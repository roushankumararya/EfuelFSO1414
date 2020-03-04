package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 2/24/18.
 */

public class FuelStationAvailabilityRequestModel {
    String isAvailable, fuelStationId, id;

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
