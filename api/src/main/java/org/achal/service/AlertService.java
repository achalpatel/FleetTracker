package org.achal.service;

import org.achal.entity.Alert;
import org.achal.entity.VehicleDetail;
import org.achal.repository.AlertRepo;
import org.achal.repository.VehicleDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertService {

    @Autowired
    AlertRepo alertRepo;

    @Autowired
    VehicleDetailRepo vehicleDetailRepo;

    @Transactional
    public void createAlert(VehicleDetail vd, String ruleName, String ruleDescription, String rulePriority) {
        Alert alert = new Alert();
        alert.setRuleName(ruleName);
        alert.setRuleDescription(ruleDescription);
        alert.setVehicleDetail(vd);
        alert.setPriority(rulePriority);
        alertRepo.save(alert);
        vd.getAlertList().add(alert);
        vehicleDetailRepo.save(vd);
    }

}
