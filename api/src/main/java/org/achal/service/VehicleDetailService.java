package org.achal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.achal.repository.TireRepo;
import org.achal.repository.VehicleDetailRepo;
import org.achal.repository.VehicleRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VehicleDetailService {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private VehicleDetailRepo vehicleDetailRepo;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public String findOne(String id) {
        VehicleDetail vd = vehicleDetailRepo.findOne(id);
        if(vd==null){
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(vd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    @Transactional
    public String create(String body) {
        JSONObject obj = new JSONObject(body);
        String vin = (String) obj.get("vin");

        Vehicle vehicle = vehicleRepository.findOne(vin);
        try {
            obj.put("vin", vehicle.toString());
            String jsonString = obj.toString();
            System.out.println("jsonString:" + jsonString);
            VehicleDetail vehicleDetail = objectMapper.readValue(jsonString, VehicleDetail.class);
            vehicleDetail.setVin(vehicle);
            vehicle.getVehicleDetailList().add(vehicleDetail);
            VehicleDetail responseObj = vehicleDetailRepo.create(vehicleDetail);
            if (responseObj == null) {
                return "{}";
            } else {
                return objectMapper.writeValueAsString(responseObj);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }

//        System.out.println(jsonString);

//        try{
//            VehicleDetail vehicleDetail = objectMapper.readValue(jsonString, VehicleDetail.class);
//            return vehicleDetailRepo.create(vehicleDetail);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//        VehicleDetail vehicleDetail = new VehicleDetail();
//        Tire tires = new Tire();
//        JSONObject tireObj = obj.getJSONObject("tires");
//        tires.setFrontLeft((float) tireObj.get("frontLeft"));
//        tires.setFrontRight((float) tireObj.get("frontRight"));
//        tires.setRearLeft((float) tireObj.get("rearLeft"));
//        tires.setRearRight((float) tireObj.get("rearRight"));
//        vehicleDetail.setVin(vehicle);
//        vehicleDetail.setLatitude((String) obj.get("latitude"));
//        vehicleDetail.setLongitude((String) obj.get("longitude"));
//        vehicleDetail.setTimestamp((LocalDateTime) obj.get("timestamp"));
//        vehicleDetail.setFuelVolume((float) obj.get("fuelVolume"));
//        vehicleDetail.setSpeed((float) obj.get("speed"));
//        vehicleDetail.setEngineHp((float) obj.get("engineHp"));
//        vehicleDetail.setCheckEngineLightOn((boolean) obj.get("checkEngineLightOn"));
//        vehicleDetail.setEngineCoolantLow((boolean) obj.get("engineCoolantLow"));
//        vehicleDetail.setCruiseControlOn((boolean) obj.get("cruiseControlOn"));
//        vehicleDetail.setEngineRpm((float) obj.get("engineRpm"));
//        vehicleDetail.setTires(tires);

//        vehicle.getVehicleDetailList().add(vehicleDetail);
//        System.out.println(vehicleDetail);
//        System.out.println(vehicleRepository.findOne(vin));
//        return vehicleDetailRepo.create(vehicleDetail);
//        VehicleDetail vd = vehicleDetailRepo.findOne(vehicleDetail.getId());
//        if (vd != null) {
//            throw new BadRequestException("VehicleDetail with id=" + vehicleDetail.getId() + " ALREADY EXISTS");
//        }
//        Vehicle v = vehicleRepository.getReference(vehicleDetail.getId());
//        System.out.println("vd: "+vehicleDetail);
//        System.out.println("v:"+v);
//        ve
//        if (v == null) {
//            throw new BadRequestException("Vehicle with id=" + vehicleDetail.getVehicle().getVin() + " DOES NOT EXISTS");
//        }
//        vehicleDetail.setVin(vehicleRepository.findOne("1HGCR2F3XFA027534"));
//        return vehicleDetailRepo.create(vehicleDetail);
    }
}
