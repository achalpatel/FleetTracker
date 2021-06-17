package org.achal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.achal.entity.Vehicle;
import org.achal.repository.VehicleRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Before
    public void setup() {
        Vehicle vehicle1 = new Vehicle("v1aaaa", LocalDateTime.now(), "make1", "model1", 2021, 98, 12, null, null);
        Vehicle vehicle2 = new Vehicle("v2aaaa", LocalDateTime.now(), "make2", "model2", 2021, 91, 12, null, null);
        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
    }

    @After
    public void destroy() {
        vehicleRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("model1")));
    }

    @Test
    public void findOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/v1aaaa"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("model1")));
    }

    @Test
    public void findOne404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/v1awdawd"))
                .andExpect(MockMvcResultMatchers.status()
                        .isNotFound());
    }

    @Test
    public void create() throws Exception {
        Vehicle vehicle3 = new Vehicle("v3aaaa", LocalDateTime.now(), "make3", "model3", 2021, 98, 12, null, null);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/vehicles")
                .content(mapper.writeValueAsString(vehicle3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vin", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("model3")));

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void create404() throws Exception {
        Vehicle vehicle2 = new Vehicle("v2aaaa", LocalDateTime.now(), "make2", "model2", 2021, 98, 12, null, null);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/vehicles")
                .content(mapper.writeValueAsString(vehicle2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void addVehicles() throws Exception{
        Vehicle vehicle3 = new Vehicle("v2aaaa", LocalDateTime.now(), "make3", "model3", 2021, 98, 12, null, null);
        Vehicle vehicle4 = new Vehicle("v4aaaa", LocalDateTime.now(), "make4", "model4", 2021, 98, 12, null, null);
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle3);
        vehicleList.add(vehicle4);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/vehicles")
                .content(mapper.writeValueAsString(vehicleList))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));


    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}