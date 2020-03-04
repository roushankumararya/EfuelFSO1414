package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 2/15/18.
 */

public class FindFuelStationManualRequestModel {
    String fuelStationId;

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public FindFuelStationManualRequestModel(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public FindFuelStationManualRequestModel() {
    }
}
