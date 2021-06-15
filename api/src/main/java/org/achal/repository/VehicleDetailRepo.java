package org.achal.repository;

import org.achal.entity.VehicleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetailRepo extends CrudRepository<VehicleDetail, String> {
}
