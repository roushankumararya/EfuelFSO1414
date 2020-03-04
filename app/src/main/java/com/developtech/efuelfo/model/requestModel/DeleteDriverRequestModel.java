package com.developtech.efuelfo.model.requestModel;

import java.util.List;

/**
 * Created by developtech on 1/16/18.
 */

public class DeleteDriverRequestModel {
    String id, fuelStationId;

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public DeleteDriverRequestModel()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeleteDriverRequestModel(String id) {
        this.id = id;
    }
}
