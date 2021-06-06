package org.achal.service;

import org.achal.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findOne(String id);
    Vehicle create(Vehicle vehicle);
    Vehicle update(String id, Vehicle vehicle);
    void delete(String id);
}