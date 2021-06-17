package org.achal.service;

import org.achal.entity.Vehicle;
import org.achal.exception.BadRequestException;
import org.achal.exception.VehicleNotFoundException;
import org.achal.repository.VehicleDetailRepo;
import org.achal.repository.VehicleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {

    @TestConfiguration
    static class VehicleServiceImplTestConfiguration {
        @Bean
        public VehicleService getService() {
            return new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    private List<Vehicle> vehicleList;


    @Before
    public void setup() {
        Vehicle vehicle1 = new Vehicle("v1aaaa", LocalDateTime.now(), "make1", "model1", 2021, 98, 12, null, null);
        Vehicle vehicle2 = new Vehicle("v2aaaa", LocalDateTime.now(), "make2", "model2", 2021, 91, 12, null, null);
        vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);

        Mockito.when(vehicleRepository.findAll())
                .thenReturn(vehicleList);
        Mockito.when(vehicleRepository.findById(vehicleList.get(0).getVin()))
                .thenReturn(Optional.of(vehicleList.get(0)));
        Mockito.when(vehicleRepository.save(vehicleList.get(0)))
                .thenReturn(vehicleList.get(0));
    }

    @After
    public void cleanup() {
        vehicleList.clear();
    }

    @Test
    public void findAll() {
        List<Vehicle> result = vehicleService.findAll();
        Assert.assertEquals("Vehicle list should match", result, vehicleList);
    }

    @Test
    public void findOne() {
        Vehicle result = vehicleService.findOne(vehicleList.get(0).getVin());
        Assert.assertEquals("Vehicle should match", result, vehicleList.get(0));
    }

    @Test(expected = VehicleNotFoundException.class)
    public void findOneNotFound() {
        vehicleService.findOne("v1aaaaaas");
    }

    @Test
    public void create() {
        Mockito.when(vehicleRepository.findById(vehicleList.get(1).getVin()))
                .thenReturn(Optional.empty());
        Mockito.when(vehicleRepository.save(vehicleList.get(1)))
                .thenReturn(vehicleList.get(1));
        Vehicle result = vehicleService.create(vehicleList.get(1));
        Assert.assertEquals("Vehicle should match", result, vehicleList.get(1));
    }

    @Test(expected = BadRequestException.class)
    public void createAlreadyExist() {
        vehicleService.create(vehicleList.get(0));
    }

    @Test
    public void update() {
        Vehicle result = vehicleService.update(vehicleList.get(0).getVin(), vehicleList.get(0));
        Assert.assertEquals("Vehicle must match", result, vehicleList.get(0));
    }

    @Test(expected = BadRequestException.class)
    public void updateIdNotMatch() {
        vehicleService.update("v2aaaa", vehicleList.get(0));
    }

    @Test(expected = VehicleNotFoundException.class)
    public void updateNotFound() {
        Mockito.when(vehicleRepository.findById(vehicleList.get(1).getVin()))
                .thenReturn(Optional.empty());
        vehicleService.update(vehicleList.get(1).getVin(), vehicleList.get(1));
    }

    @Test
    public void delete() {
    }

    @Test(expected = VehicleNotFoundException.class)
    public void deleteNotFound() {
        vehicleService.delete("v4111");
    }

    @Test
    public void addVehicles() {
        Mockito.when(vehicleRepository.saveAll(vehicleList))
                .thenReturn(vehicleList);
        List<Vehicle> result = vehicleService.addVehicles(vehicleList);
        Assert.assertEquals("vehicle list must match", result, vehicleList);
    }
}