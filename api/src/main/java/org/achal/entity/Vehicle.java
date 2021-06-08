package org.achal.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NamedQueries({
        @NamedQuery(name = "Vehicle.findOne", query = "SELECT veh FROM Vehicle veh WHERE veh.vin = :id"),
        @NamedQuery(name = "Vehicle.findAll", query = "SELECT veh FROM Vehicle veh")
})
public class Vehicle {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    String vin;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDateTime lastServiceDate;

    String make;
    String model;
    int year;
    float redLineRpm;
    float maxFuelVolume;



    public Vehicle() {
    }

    public Vehicle(String vin, String make, String model, int year, float redLineRpm, float maxFuelVolume, LocalDateTime lastServiceDate) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.redLineRpm = redLineRpm;
        this.maxFuelVolume = maxFuelVolume;
        this.lastServiceDate = lastServiceDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRedLineRpm() {
        return redLineRpm;
    }

    public void setRedLineRpm(int redLineRpm) {
        this.redLineRpm = redLineRpm;
    }

    public float getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(int maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public LocalDateTime getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDateTime lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }
}
