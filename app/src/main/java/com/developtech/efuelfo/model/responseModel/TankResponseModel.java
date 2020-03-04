package com.developtech.efuelfo.model.responseModel;

/**
 * Created by dt on 2/24/18.
 */

public class TankResponseModel {
    FuelStationResponseModel fuelStation;
    String fuelType, tankName, fuelCapacity, density, temperature, createdAt, updatedAt, id, OFQty, CFQty;

    public String getOFQty() {
        return OFQty;
    }

    public void setOFQty(String OFQty) {
        this.OFQty = OFQty;
    }

    public String getCFQty() {
        return CFQty;
    }

    public void setCFQty(String CFQty) {
        this.CFQty = CFQty;
    }

    public FuelStationResponseModel getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStationResponseModel fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
