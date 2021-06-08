package org.achal.controller;

import org.achal.entity.Vehicle;
import org.achal.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value = "vehicles")
@CrossOrigin(origins = "http://mocker.egen.academy/")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Vehicle findOne(@PathVariable("id") String id) {
        return vehicleService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicle;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> addVehicles(@RequestBody  List<Vehicle> vehicles){
        return vehicleService.addVehicles(vehicles);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Vehicle update(@PathVariable("id") String id, @RequestBody Vehicle vehicle) {
        return vehicleService.update(id, vehicle);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String id) {
        vehicleService.delete(id);
    }
}
