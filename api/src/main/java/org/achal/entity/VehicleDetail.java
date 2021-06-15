package org.achal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class VehicleDetail {

    @Id
    String id;

//    @JsonIgnore
    @JsonProperty("vin")
    @ManyToOne(fetch=FetchType.LAZY)
    Vehicle vin;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime timestamp;

    @OneToOne(cascade = CascadeType.PERSIST)
    Tire tires;

    String latitude;
    String longitude;
    float fuelVolume;
    float speed;
    float engineHp;
    boolean checkEngineLightOn;
    boolean engineCoolantLow;
    boolean cruiseControlOn;
    float engineRpm;


    public VehicleDetail() {
        id = UUID.randomUUID().toString();
    }


    public VehicleDetail(Vehicle vin, LocalDateTime timestamp, Tire tires, String latitude, String longitude, float fuelVolume, float speed, float engineHp, boolean checkEngineLightOn, boolean engineCoolantLow, boolean cruiseControlOn, float engineRpm) {
        this.vin = vin;
        this.timestamp = timestamp;
        this.tires = tires;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fuelVolume = fuelVolume;
        this.speed = speed;
        this.engineHp = engineHp;
        this.checkEngineLightOn = checkEngineLightOn;
        this.engineCoolantLow = engineCoolantLow;
        this.cruiseControlOn = cruiseControlOn;
        this.engineRpm = engineRpm;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVin() {
        return vin;
    }

    public void setVin(Vehicle vehicle) {
        this.vin = vehicle;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public float getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(float fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(float engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLigthOn) {
        this.checkEngineLightOn = checkEngineLigthOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public float getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(float engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Tire getTires() {
        return tires;
    }

    public void setTires(Tire tire) {
        this.tires = tire;
    }

//    @Override
//    public String toString() {
//        return "{ " + "\n" +
//                "  id:" + id + "\n" +
//                ", vin:" + vin.getVin() + "\n" +
//                ", timestamp:" + timestamp + "\n" +
//                ", tires:" + tires + "\n" +
//                ", latitude:" + latitude + "\n" +
//                ", longitude:" + longitude + "\n" +
//                ", fuelVolume:" + fuelVolume + "\n" +
//                ", speed:" + speed + "\n" +
//                ", engineHp:" + engineHp + "\n" +
//                ", checkEngineLightOn:" + checkEngineLightOn + "\n" +
//                ", engineCoolantLow:" + engineCoolantLow + "\n" +
//                ", cruiseControlOn:" + cruiseControlOn + "\n" +
//                ", engineRpm:" + engineRpm + "\n" +
//                '}';
//    }
}
