package org.achal.service;

import org.achal.entity.Alert;
import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.achal.repository.AlertRepo;
import org.achal.repository.VehicleDetailRepo;
import org.achal.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    @Autowired
    private AlertRepo alertRepo;

    @Autowired
    private VehicleDetailRepo vehicleDetailRepo;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public void createAlert(VehicleDetail vd, String ruleName, String ruleDescription, String rulePriority) {
        Alert alert = new Alert();
        Vehicle vehicle = vd.getVin();
        alert.setRuleName(ruleName);
        alert.setRuleDescription(ruleDescription);
        alert.setVehicleDetail(vd);
        alert.setPriority(rulePriority);
        alert.setVehicle(vehicle);
        alertRepo.save(alert);
        vehicle.getAlertList().add(alert);
        vehicleRepository.save(vehicle);
        vd.getAlertList().add(alert);
        vehicleDetailRepo.save(vd);
    }

    @Transactional
    public List<Alert> findAlertsByVehicle(String id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(value -> alertRepo.findAlertsByVehicle(value)).orElse(null);
    }

}
