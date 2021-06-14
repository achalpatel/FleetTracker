package org.achal.repository;

import org.achal.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findAll", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public Vehicle findOne(String id) {
        return em.find(Vehicle.class, id);
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        em.persist(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return em.merge(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        em.remove(vehicle);
    }

    @Override
    public Vehicle getReference(String id) {
        return em.getReference(Vehicle.class, id);
    }
}
