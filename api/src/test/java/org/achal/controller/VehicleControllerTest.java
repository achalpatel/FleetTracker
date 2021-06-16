package org.achal.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void create() {
    }

    @Test
    public void addVehicles() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}