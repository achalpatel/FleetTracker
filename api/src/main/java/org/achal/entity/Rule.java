package org.achal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Rule {
    @Id
    String ruleId;

    int ruleNumber;
    String priority;
}
