package com.developtech.efuelfo.model.requestModel;

public class UpdateOperatorRequestModel {
    private String id, fuelStationId;
    private boolean isManager;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
