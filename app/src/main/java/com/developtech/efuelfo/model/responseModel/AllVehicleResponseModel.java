package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dt on 1/10/18.
 */

public class AllVehicleResponseModel implements Serializable {

    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("fuelType")
    @Expose
    private String fuelType;
    @SerializedName("fuelCapacity")
    @Expose
    private String fuelCapacity;

    @SerializedName("vehicleOwner")
    @Expose
    private String vehicleOwner;
    @SerializedName("id")
    @Expose
    private String id;

    boolean isSelected;

    GetDriverResponseModel driver;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private String image, vehicleCategory, vehicleType;

    private boolean selfDriven;

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isSelfDriven() {
        return selfDriven;
    }

    public void setSelfDriven(boolean selfDriven) {
        this.selfDriven = selfDriven;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
