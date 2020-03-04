package com.developtech.efuelfo.model.requestModel;

import java.util.List;

/**
 * Created by dt on 3/27/18.
 */

public class TransRecievedRequestModel {
    List<String> vehicleOwner;
    String fuelStationId;

    public List<String> getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(List<String> vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }
}
