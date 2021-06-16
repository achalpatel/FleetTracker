package org.achal.service;

import org.achal.entity.Tire;
import org.achal.repository.TireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TireService {
    @Autowired
    TireRepo tireRepo;

    @Transactional
    public Tire create(Tire tire){
        return tireRepo.save(tire);
    }
}
