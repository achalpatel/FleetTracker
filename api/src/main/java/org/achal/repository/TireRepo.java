package org.achal.repository;

import org.achal.entity.Tire;
import org.achal.entity.VehicleDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TireRepo {
    @PersistenceContext
    private EntityManager em;

    public Tire findOne(String id) {
        return em.find(Tire.class, id);
    }

    public Tire create(Tire tire){
        em.persist(tire);
        return tire;
    }
}
