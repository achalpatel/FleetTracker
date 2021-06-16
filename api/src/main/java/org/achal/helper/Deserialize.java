package org.achal.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;

import org.achal.service.VehicleService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Deserialize {

    @Autowired
    private VehicleService vehicleService;

    public VehicleDetail getVehicleDetail(String body) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject obj = new JSONObject(body);
//        String vin = (String) obj.get("vin");
//        System.out.println("vin:"+vin);
//        Vehicle vehicle = vehicleService.findOne(vin);
//        if (vehicle == null) {
//            return null;
//        }
//        try {
//            VehicleDetail vehicleDetail = objectMapper.readValue(obj.toString(), VehicleDetail.class);
//            vehicleDetail.setVin(vehicle);
//            return vehicleDetail;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }
}
