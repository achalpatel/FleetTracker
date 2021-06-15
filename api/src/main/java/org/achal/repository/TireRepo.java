package org.achal.repository;

import org.achal.entity.Tire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TireRepo extends CrudRepository<Tire, String> {
}
