package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 2/28/18.
 */

public class VerifyTransactionRequestModel {
    String vehicleNumber, fuelStationId;
    int limit, page;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public VerifyTransactionRequestModel(String vehicleNumber, String fuelStationId) {
        this.vehicleNumber = vehicleNumber;
        this.fuelStationId = fuelStationId;
    }
}
