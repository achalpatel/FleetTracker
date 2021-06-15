package org.achal.service;

import org.achal.entity.Vehicle;
import org.achal.exception.BadRequestException;
import org.achal.exception.VehicleNotFoundException;
import org.achal.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public Vehicle findOne(String id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (!vehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id=" + id + " NOT FOUND");
        }
        return vehicle.get();
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle vehicle) {
        Optional<Vehicle> v = vehicleRepository.findById(vehicle.getVin());
        if (v.isPresent()) {
            throw new BadRequestException("Vehicle with id=" + vehicle.getVin() + " ALREADY EXISTS");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle update(String id, Vehicle vehicle) {
        if (!id.equals(vehicle.getVin())) {
            throw new BadRequestException("id:" + id + " DOES NOT MATCH vehicle.vin:" + vehicle.getVin());
        }
        Optional<Vehicle> v = vehicleRepository.findById(id);
        if (!v.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id=" + id + " DOES NOT EXISTS");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Vehicle> v = vehicleRepository.findById(id);
        if (!v.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id=" + id + " DOES NOT EXISTS");
        }
        vehicleRepository.delete(v.get());
    }

    @Override
    @Transactional
    public List<Vehicle> addVehicles(List<Vehicle> vehicleList) {
        return (List<Vehicle>) vehicleRepository.saveAll(vehicleList);
    }
}
