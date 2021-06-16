package org.achal.service;

import org.achal.entity.VehicleDetail;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyRules {

    @Autowired
    AlertService alertService;

    Map<String, Object> map = new HashMap<>();

    public void runRuleBuilder(VehicleDetail vehicleDetail) {
        Facts facts = addFacts(vehicleDetail);
        List<Rule> ruleList = buildRules(vehicleDetail);
        Rules rules = new Rules();
        ruleList.forEach(rule -> rules.register(rule));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }


    public Facts addFacts(VehicleDetail vehicleDetail) {
//        Fact variables : engineRpm, readlineRpm, fuelVolume, maxFuelVolume, tire pressure, engineCoolantLow, checkEngineLightOn
//        System.out.println("Vehicle : "+vehicleDetail.getVin().getVin());
        map.put("engineRpm", vehicleDetail.getEngineRpm());
        map.put("readlineRpm", vehicleDetail.getVin().getRedlineRpm());
        map.put("fuelVolume", vehicleDetail.getFuelVolume());
        map.put("maxFuelVolume", vehicleDetail.getVin().getMaxFuelVolume());
        map.put("frontLeft", vehicleDetail.getTires().getFrontLeft());
        map.put("frontRight", vehicleDetail.getTires().getFrontRight());
        map.put("rearLeft", vehicleDetail.getTires().getRearLeft());
        map.put("rearRight", vehicleDetail.getTires().getRearRight());
        map.put("engineCoolantLow", vehicleDetail.isEngineCoolantLow());
        map.put("checkEngineLightOn", vehicleDetail.isCheckEngineLightOn());

        Facts facts = new Facts();
        facts.add(new Fact<Float>("engineRpm", vehicleDetail.getEngineRpm()));
        facts.add(new Fact<Float>("readlineRpm", vehicleDetail.getVin().getRedlineRpm()));
        facts.add(new Fact<Float>("fuelVolume", vehicleDetail.getFuelVolume()));
        facts.add(new Fact<Float>("maxFuelVolume", vehicleDetail.getVin().getMaxFuelVolume()));
        facts.add(new Fact<Float>("frontLeft", vehicleDetail.getTires().getFrontLeft()));
        facts.add(new Fact<Float>("frontRight", vehicleDetail.getTires().getFrontRight()));
        facts.add(new Fact<Float>("rearLeft", vehicleDetail.getTires().getRearLeft()));
        facts.add(new Fact<Float>("rearRight", vehicleDetail.getTires().getRearRight()));
        facts.add(new Fact<Boolean>("engineCoolantLow", vehicleDetail.isEngineCoolantLow()));
        facts.add(new Fact<Boolean>("checkEngineLightOn", vehicleDetail.isCheckEngineLightOn()));
        return facts;
    }

    public List<Rule> buildRules(VehicleDetail vd) {
        List<Rule> rules = new ArrayList<>();
        String rpmRuleName = "rpmRule";
        String rpmRuleDescription = "Engine rpm is greater than readlineRpm";
        String rpmRulePriority = "HIGH";
        Rule rpmRule = new RuleBuilder()
                .name(rpmRuleName)
                .description(rpmRuleDescription)
                .when(facts -> (float) facts.get("engineRpm") > (float) facts.get("readlineRpm"))
                .then(facts -> alertService.createAlert(vd, rpmRuleName, rpmRuleDescription, rpmRulePriority))
                .build();
        rules.add(rpmRule);


        String fuelVolumeRuleName = "fuelVolumeRule";
        String fuelVolumeRuleDescription = "Fuel is less than 10% of the maxFuelVolume";
        String fuelVolumeRulePriority = "MEDIUM";
        Rule fuelVolumeRule = new RuleBuilder()
                .name(fuelVolumeRuleName)
                .description(fuelVolumeRuleDescription)
                .when(facts -> (float) facts.get("fuelVolume") < (0.01 * (float) facts.get("maxFuelVolume")))
                .then(facts -> alertService.createAlert(vd, fuelVolumeRuleName, fuelVolumeRuleDescription, fuelVolumeRulePriority))
                .build();
        rules.add(fuelVolumeRule);


        String tireRuleName = "tireRule";
        String tireRuleDescription = "Tire pressure is < 32psi or >3 6psi";
        String tireRulePriority = "LOW";
        Rule tireRule = new RuleBuilder()
                .name(tireRuleName)
                .description(tireRuleDescription)
                .when(facts -> {
                    float frontLeft = facts.get("frontLeft");
                    float frontRight = facts.get("frontRight");
                    float rearLeft = facts.get("rearLeft");
                    float rearRight = facts.get("rearRight");
                    return (frontLeft < 32.0 || frontLeft > 36.0
                            || frontRight < 32.0 || frontRight > 36.0
                            || rearLeft < 32.0 || rearLeft > 36.0
                            || rearRight < 32.0 || rearRight > 36.0);
                })
                .then(facts -> alertService.createAlert(vd, tireRuleName, tireRuleDescription, tireRulePriority))
                .build();
        rules.add(tireRule);


        String engineCoolantRuleName = "engineCoolantRule";
        String engineCoolantRuleDescription = "Engine coolant is low";
        String engineCoolantRulePriority = "LOW";
        Rule engineCoolantRule = new RuleBuilder()
                .name(engineCoolantRuleName)
                .description(engineCoolantRuleDescription)
                .when(facts -> facts.get("engineCoolantLow"))
                .then(facts -> alertService.createAlert(vd, engineCoolantRuleName, engineCoolantRuleDescription, engineCoolantRulePriority))
                .build();
        rules.add(engineCoolantRule);


        String checkEngineLightRuleName = "checkEngineLightRule";
        String checkEngineLightRuleDescription = "Engine Light On";
        String checkEngineLightRulePriority = "LOW";
        Rule checkEngineLightRule = new RuleBuilder()
                .name(checkEngineLightRuleName)
                .description(checkEngineLightRuleDescription)
                .when(facts -> facts.get("checkEngineLightOn"))
                .then(facts -> alertService.createAlert(vd, checkEngineLightRuleName, checkEngineLightRuleDescription, checkEngineLightRulePriority))
                .build();
        rules.add(checkEngineLightRule);
        return rules;
    }

}
