package org.achal.repository;

import org.achal.entity.Vehicle;
import org.achal.entity.VehicleDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class VehicleDetailRepo {
    @PersistenceContext
    private EntityManager em;

    public VehicleDetail findOne(String id) {
        return em.find(VehicleDetail.class, id);
    }

    public VehicleDetail create(VehicleDetail vehicleDetail){
        em.persist(vehicleDetail);
        return vehicleDetail;
    }
}
