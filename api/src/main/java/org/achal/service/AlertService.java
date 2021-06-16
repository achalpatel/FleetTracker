package org.achal.service;

import org.achal.entity.Alert;
import org.achal.entity.VehicleDetail;
import org.achal.repository.AlertRepo;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertService {

    @Autowired
    AlertRepo alertRepo;

    public void addFacts(VehicleDetail vehicleDetail){
//        Fact variables : engineRpm, readlineRpm, fuelVolume, maxFuelVolume, tire pressure, engineCoolantLow, checkEngineLightOn
        Facts facts = new Facts();
        facts.add(new Fact<Float>("engineRpm", vehicleDetail.getEngineRpm()));
        facts.add(new Fact<Float>("readlineRpm", vehicleDetail.getVin().getRedLineRpm()));
        facts.add(new Fact<Float>("fuelVolume", vehicleDetail.getFuelVolume()));
        facts.add(new Fact<Float>("maxFuelVolume", vehicleDetail.getVin().getMaxFuelVolume()));
        facts.add(new Fact<Float>("frontLeft", vehicleDetail.getTires().getFrontLeft()));
        facts.add(new Fact<Float>("frontRight", vehicleDetail.getTires().getFrontRight()));
        facts.add(new Fact<Float>("rearLeft", vehicleDetail.getTires().getRearLeft()));
        facts.add(new Fact<Float>("rearRight", vehicleDetail.getTires().getRearRight()));
        facts.add(new Fact<Boolean>("engineCoolantLow", vehicleDetail.isEngineCoolantLow()));
        facts.add(new Fact<Boolean>("checkEngineLightOn", vehicleDetail.isCheckEngineLightOn()));

        Rule rpmRule = buildRule(vehicleDetail);
        Rules rules = new Rules();
        rules.register(rpmRule);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

    }

    public Rule buildRule(VehicleDetail vd){
        String rpmRuleName = "rpmRule";
        String rpmRuleDescription = "Engine rpm is greater than readlineRpm";
        return new RuleBuilder()
                .name(rpmRuleName)
                .description(rpmRuleDescription)
                .when(facts -> (float)facts.get("engineRpm")>(float)facts.get("readlineRpm"))
                .then(facts -> createAlert(vd, rpmRuleName, rpmRuleDescription))
                .build();
    }

    @Transactional
    public void createAlert(VehicleDetail vd, String ruleName, String ruleDescription){
        Alert alert = new Alert();
        alert.setRuleName(ruleName);
        alert.setRuleDescription(ruleDescription);
        alert.setVehicleDetail(vd);
        alertRepo.save(alert);
    }

}
