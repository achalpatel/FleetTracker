package org.achal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.achal.entity.Geolocation;
import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.achal.exception.VehicleNotFoundException;
import org.achal.repository.VehicleDetailRepo;
import org.achal.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleDetailRepo vehicleDetailRepo;

    @Transactional
    public String getVehicleLocation(String id) {
        Optional<Vehicle> result = vehicleRepository.findById(id);
        if (!result.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with vin:" + id + "NOT FOUND");
        }
        List<VehicleDetail> vehicleDetailList = vehicleDetailRepo.getVehicleDetailsByVinAndTimestampAfter(
                result.get(), LocalDateTime.now().minusMinutes(30)
        );
        List<Geolocation> geolocationList = new ArrayList<>();
        vehicleDetailList.forEach(vd -> {
            Geolocation geolocation = new Geolocation();
            geolocation.setLongitude(vd.getLongitude());
            geolocation.setLatitude(vd.getLatitude());
            geolocationList.add(geolocation);
        });
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(geolocationList);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
