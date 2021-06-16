package org.achal.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Alert {
    @Id
    String id;

    @ManyToOne
    VehicleDetail vehicleDetail;

    String ruleName;

    String ruleDescription;

    String priority;

    public Alert() {
        id = UUID.randomUUID().toString();
    }

    public Alert(VehicleDetail vehicleDetail, String ruleName, String ruleDescription, String priority) {
        this.vehicleDetail = vehicleDetail;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
