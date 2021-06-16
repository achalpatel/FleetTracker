package org.achal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.achal.entity.Tire;
import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.achal.repository.VehicleDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class VehicleDetailService {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private VehicleDetailRepo vehicleDetailRepo;

    @Autowired
    private TireService tireService;

    @Autowired
    private VehicleService vehicleService;

    @Transactional
    public String findOne(String id) {
        Optional<VehicleDetail> vd = vehicleDetailRepo.findById(id);
        if (!vd.isPresent()) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(vd.get());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    @Transactional
    public VehicleDetail create(VehicleDetail vehicleDetail) {
        Vehicle vehicle = vehicleDetail.getVin();
        Tire tire = vehicleDetail.getTires();
        tireService.create(tire);
        vehicle.getVehicleDetailList().add(vehicleDetail);
        return vehicleDetailRepo.save(vehicleDetail);
    }
}
