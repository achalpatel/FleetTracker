package org.achal.service;

import org.achal.entity.Vehicle;
import org.achal.exception.VehicleNotFoundException;
import org.achal.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle findOne(String id) {
        Vehicle vehicle = vehicleRepository.findOne(id);
        if(vehicle == null){
            throw new VehicleNotFoundException("Vehicle with id="+id+" NOT FOUND");
        }else{
            return vehicle;
        }
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle update(String id, Vehicle vehicle) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}