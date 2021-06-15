package org.achal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.achal.entity.Tire;
import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.achal.exception.BadRequestException;
import org.achal.repository.TireRepo;
import org.achal.repository.VehicleDetailRepo;
import org.achal.repository.VehicleRepository;
import org.json.JSONObject;
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
    private TireRepo tireRepo;

    @Transactional
    public String findOne(String id) {
        Optional<VehicleDetail> vd = vehicleDetailRepo.findById(id);
        if(!vd.isPresent()){
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
    public VehicleDetail create(VehicleDetail vd) {
        Tire tire = vd.getTires();
        tireRepo.save(tire);
        return vehicleDetailRepo.save(vd);
//        JSONObject obj = new JSONObject(body);
//        String vin = (String) obj.get("vin");
//        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
//        if(!vehicle.isPresent()){
//            throw new BadRequestException("Vehicle with vin=" + vin + " NOT FOUND");
//        }
//        try {
//            VehicleDetail vehicleDetail = objectMapper.readValue(obj.toString(), VehicleDetail.class);
//            vehicleDetail.setVin(vehicle.get());
//            vehicle.get().getVehicleDetailList().add(vehicleDetail);
//            VehicleDetail responseObj = vehicleDetailRepo.save(vehicleDetail);
//            return objectMapper.writeValueAsString(responseObj);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "{}";
//        }
    }
}
