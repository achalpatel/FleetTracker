package org.achal.service;

import org.achal.entity.Vehicle;
import org.achal.exception.VehicleNotFoundException;
import org.achal.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findOne(String id) {
        Vehicle vehicle = vehicleRepository.findOne(id);
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle with id=" + id + " NOT FOUND");
        } else {
            return vehicle;
        }
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    @Override
    @Transactional
    public Vehicle update(String id, Vehicle vehicle) {
        return null;
    }

    @Override
    @Transactional
    public void delete(String id) {

    }

    @Override
    @Transactional
    public List<Vehicle> addVehicles(List<Vehicle> vehicleList){
        List<Vehicle> updatedVehicleList = new ArrayList<>();
        for(Vehicle v : vehicleList){
            updatedVehicleList.add(put(v));
        }
        return updatedVehicleList;
    }

    @Transactional
    public Vehicle put(Vehicle vehicle){
        try{
            findOne(vehicle.getVin());
            return update(vehicle.getVin(), vehicle);
        }catch (VehicleNotFoundException exception){
            return create(vehicle);
        }
    }

}
