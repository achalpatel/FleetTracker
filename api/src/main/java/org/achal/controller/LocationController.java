package org.achal.controller;
import org.achal.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "location")
@CrossOrigin(origins = "http://mocker.egen.academy/")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public String getVehicleLocation(@PathVariable("id") String id){
        return locationService.getVehicleLocation(id);
    }

}
