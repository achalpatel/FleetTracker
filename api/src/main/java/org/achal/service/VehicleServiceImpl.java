package org.achal.service;

import org.achal.entity.Vehicle;
import org.achal.exception.BadRequestException;
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
        Vehicle v = vehicleRepository.findOne(vehicle.getVin());
        if (v != null) {
            throw new BadRequestException("Vehicle with id=" + vehicle.getVin() + " ALREADY EXISTS");
        }
        return vehicleRepository.create(vehicle);
    }

    @Override
    @Transactional
    public Vehicle update(String id, Vehicle vehicle) {
        return vehicleRepository.update(id, vehicle);
    }

    @Override
    @Transactional
    public void delete(String id) {
        vehicleRepository.delete(id);
    }

    @Override
    @Transactional
    public List<Vehicle> addVehicles(List<Vehicle> vehicleList) {
        List<Vehicle> updatedVehicleList = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            updatedVehicleList.add(put(v));
        }
        return updatedVehicleList;
    }

    @Transactional
    public Vehicle put(Vehicle vehicle) {
        Vehicle v = vehicleRepository.findOne(vehicle.getVin());
        if (v == null) {
            return vehicleRepository.create(vehicle);
        }else{
            return vehicleRepository.update(vehicle.getVin(), vehicle);
        }
    }

}
