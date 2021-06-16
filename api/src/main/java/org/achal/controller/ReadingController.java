package org.achal.controller;


import org.achal.entity.VehicleDetail;
import org.achal.exception.BadRequestException;
import org.achal.helper.Deserialize;
import org.achal.service.AlertService;
import org.achal.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "readings")
@CrossOrigin(origins = "http://mocker.egen.academy/")
public class ReadingController {

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private Deserialize deserializeHelper;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public String findOne(@PathVariable("id") String id) {
        return vehicleDetailService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public VehicleDetail create(@RequestBody String body) {
        VehicleDetail vehicleDetail = deserializeHelper.getVehicleDetail(body);
        if (vehicleDetail == null) {
            throw new BadRequestException("Vehicle NOT FOUND");
        }
        VehicleDetail responseObj = vehicleDetailService.create(vehicleDetail);
        alertService.addFacts(responseObj);
        return responseObj;
    }
}
