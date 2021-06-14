package org.achal.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    String vin;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime lastServiceDate;

    String make;
    String model;
    int year;
    float redLineRpm;
    float maxFuelVolume;

    @JsonIgnore
    @OneToMany(mappedBy = "vin")
    List<VehicleDetail> vehicleDetailList;

    public Vehicle() {
    }

    public Vehicle(String vin, LocalDateTime lastServiceDate, String make, String model, int year, float redLineRpm, float maxFuelVolume, List<VehicleDetail> vehicleDetailList) {
        this.vin = vin;
        this.lastServiceDate = lastServiceDate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.redLineRpm = redLineRpm;
        this.maxFuelVolume = maxFuelVolume;
        this.vehicleDetailList = vehicleDetailList;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LocalDateTime getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDateTime lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
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

    public void setRedLineRpm(float redLineRpm) {
        this.redLineRpm = redLineRpm;
    }

    public float getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(float maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public List<VehicleDetail> getVehicleDetailList() {
        return vehicleDetailList;
    }

    public void setVehicleDetailList(List<VehicleDetail> vehicleDetailList) {
        this.vehicleDetailList = vehicleDetailList;
    }

    @Override
    public String toString() {
        return "{  vin:" + vin +
                ", lastServiceDate:" + lastServiceDate +
                ", make:" + make +
                ", model:" + model +
                ", year:" + year +
                ", redLineRpm:" + redLineRpm +
                ", maxFuelVolume:" + maxFuelVolume +
                "}";
    }
}
