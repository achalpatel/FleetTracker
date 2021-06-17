package org.achal.controller;

import org.achal.entity.Alert;
import org.achal.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "alerts")
@CrossOrigin(origins = "http://mocker.egen.academy/")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @RequestMapping(method = RequestMethod.GET, value = "vehicle/{id}")
    public List<Alert> findAlertsByVehicle(@PathVariable("id") String id){
        return alertService.findAlertsByVehicle(id);
    }

}
