package org.achal.repository;

import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleDetailRepo extends CrudRepository<VehicleDetail, String> {
    List<VehicleDetail> getVehicleDetailsByVinAndTimestampAfter(Vehicle vehicle, LocalDateTime time);
}
