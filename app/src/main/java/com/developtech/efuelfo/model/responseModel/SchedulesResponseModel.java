package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 2/22/18.
 */

public class SchedulesResponseModel implements Serializable{
    FuelStationResponseModel fuelStation;
    List<FuelDetailModel> fuelDetail;
    String time, createdAt, id, updatedAt;

    public FuelStationResponseModel getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStationResponseModel fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<FuelDetailModel> getFuelDetail() {
        return fuelDetail;
    }

    public void setFuelDetail(List<FuelDetailModel> fuelDetail) {
        this.fuelDetail = fuelDetail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
