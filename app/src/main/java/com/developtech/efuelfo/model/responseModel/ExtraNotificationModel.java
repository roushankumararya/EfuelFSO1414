package com.developtech.efuelfo.model.responseModel;

/**
 * Created by dt on 3/7/18.
 */

public class ExtraNotificationModel {
    String requestId, fuelStationId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }
}
