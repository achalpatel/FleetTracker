package org.achal.repository;

import org.achal.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository{
    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle findOne(String id) {
        return null;
    }
}
