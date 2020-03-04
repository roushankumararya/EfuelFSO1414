package com.developtech.efuelfo.model.responseModel;

import com.developtech.efuelfo.model.VehicleOwnerHomeModel;

import java.util.List;

/**
 * Created by dt on 2/23/18.
 */

public class VehicleByNumberResponseModel {
    GetDriverResponseModel driver;
    String image, vehicleCategory, company, model, color, alias, vehicleNumber, fuelType, fuelCapacity, vehicleType, createdAt, updatedAt, id;
    boolean selfDriven, isDeleted;
    List<SchedulesResponseModel> schedules;
    VehicleOwnerResponseModel vehicleOwner;


    public VehicleOwnerResponseModel getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(VehicleOwnerResponseModel vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public GetDriverResponseModel getDriver() {
        if (driver == null) {
            driver = new GetDriverResponseModel();
            driver.setMobileNumber("");
            driver.setFirstName("");
            driver.setLastName("");

        }
        return driver;
    }

    public void setDriver(GetDriverResponseModel driver) {
        this.driver = driver;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public boolean isSelfDriven() {
        return selfDriven;
    }

    public void setSelfDriven(boolean selfDriven) {
        this.selfDriven = selfDriven;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<SchedulesResponseModel> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<SchedulesResponseModel> schedules) {
        this.schedules = schedules;
    }
}
