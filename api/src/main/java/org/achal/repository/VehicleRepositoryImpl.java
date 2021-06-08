package org.achal.repository;

import org.achal.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle findOne(String id) {
        return null;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        em.persist(vehicle);
        return null;
    }

    @Override
    public Vehicle update(String id, Vehicle vehicle) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
