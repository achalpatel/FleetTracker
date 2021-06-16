package org.achal.repository;

import org.achal.entity.Alert;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepo extends CrudRepository<Alert, String> {
}
