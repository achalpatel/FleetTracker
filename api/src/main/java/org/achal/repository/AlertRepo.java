package org.achal.repository;

import org.achal.entity.Alert;
import org.achal.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertRepo extends CrudRepository<Alert, String> {
    List<Alert> findAlertsByVehicle(Vehicle vehicle);
    List<Alert> findAlertsByTimestampAfter(LocalDateTime time);
}
