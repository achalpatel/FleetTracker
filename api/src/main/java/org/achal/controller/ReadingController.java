package org.achal.controller;


import org.achal.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "readings")
@CrossOrigin(origins = "http://mocker.egen.academy/")
public class ReadingController {

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public String findOne(@PathVariable("id") String id) {
        return vehicleDetailService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestBody String body) {
        return vehicleDetailService.create(body);
    }
}
