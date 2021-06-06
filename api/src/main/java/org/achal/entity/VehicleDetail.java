package org.achal.entity;

public class VehicleDetail {
    String vin;
    String latitude;
    String longitude;
    String timestamp;
    float fuelVolume;
    float speed;
    float engineHp;
    boolean checkEngineLigthOn;
    boolean engineCoolantLow;
    boolean cruiseControlOn;
    float engineRpm;
    Tire tire;

    public VehicleDetail(String vin, String latitude, String longitude, String timestamp, float fuelVolume, float speed, float engineHp, boolean checkEngineLigthOn, boolean engineCoolantLow, boolean cruiseControlOn, float engineRpm, Tire tire) {
        this.vin = vin;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.fuelVolume = fuelVolume;
        this.speed = speed;
        this.engineHp = engineHp;
        this.checkEngineLigthOn = checkEngineLigthOn;
        this.engineCoolantLow = engineCoolantLow;
        this.cruiseControlOn = cruiseControlOn;
        this.engineRpm = engineRpm;
        this.tire = tire;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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

    public boolean isCheckEngineLigthOn() {
        return checkEngineLigthOn;
    }

    public void setCheckEngineLigthOn(boolean checkEngineLigthOn) {
        this.checkEngineLigthOn = checkEngineLigthOn;
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

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }
}
