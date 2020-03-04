package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 2/23/18.
 */

public class VehicleByNumberRequestModel {
    String fuelStationId, vehicleNumber;

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
