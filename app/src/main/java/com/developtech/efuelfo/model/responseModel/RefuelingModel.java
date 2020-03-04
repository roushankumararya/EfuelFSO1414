package com.developtech.efuelfo.model.responseModel;

public class RefuelingModel {
    String fuelType;
    float volByFuelType, totalSum;

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getVolByFuelType() {
        return volByFuelType;
    }

    public void setVolByFuelType(float volByFuelType) {
        this.volByFuelType = volByFuelType;
    }
}
