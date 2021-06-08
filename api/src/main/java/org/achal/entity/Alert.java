package org.achal.entity;

import javax.persistence.*;


public class Alert {
    @Id
    String alertId;

    @ManyToOne
    Vehicle vehicle;

    @ManyToOne
    Rule rule;


}
