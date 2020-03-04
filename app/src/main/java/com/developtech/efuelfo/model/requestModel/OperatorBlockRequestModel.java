package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 2/7/18.
 */

public class OperatorBlockRequestModel {
    public String fuelStationId, operatorId, status;

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
