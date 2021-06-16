package org.achal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.achal.entity.Tire;
import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.achal.exception.BadRequestException;
import org.achal.repository.VehicleDetailRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("vehicleServiceImpl")
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
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject obj = new JSONObject(body);
//        String vin = (String) obj.get("vin");
        Vehicle vehicle = vehicleDetail.getVin();
        Tire tire = vehicleDetail.getTires();
        tireService.create(tire);
        VehicleDetail response = vehicleDetailRepo.save(vehicleDetail);;
        vehicle.getVehicleDetailList().add(response);
        vehicleService.update(vehicle.getVin(), vehicle);
        return response;
    }
}
