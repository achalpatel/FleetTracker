package org.achal.repository;

import org.achal.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> findAll();
    Vehicle findOne(String id);
}
