package com.developtech.efuelfo.model.responseModel;

/**
 * Created by developtech on 1/22/18.
 */

public class CompanyListResponseModel {
    String name, id, fuelCapacity;


    FuelTypeResponseModel fuelType;

    public FuelTypeResponseModel getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeResponseModel fuelType) {
        this.fuelType = fuelType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }
}
