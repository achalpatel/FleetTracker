package org.achal.entity;

import java.util.UUID;

public class Vehicle {
    String vin;
    String make;
    String model;
    int year;
    float redLineRpm;
    float maxFuelVolume;
    String lastServiceDate;

    public Vehicle() {
    }

    public Vehicle(String vin, String make, String model, int year, float redLineRpm, float maxFuelVolume, String lastServiceDate) {
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

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }
}