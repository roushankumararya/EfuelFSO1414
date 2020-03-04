package com.developtech.efuelfo.model.requestModel;

import com.developtech.efuelfo.model.responseModel.FuelDetailModel;

import java.util.List;

/**
 * Created by dt on 2/21/18.
 */

public class CreateScheduleRequestModel {
    String time, fuelStation, requestId;
    List<FuelDetailModel> fuelDetail;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public List<FuelDetailModel> getFuelDetail() {
        return fuelDetail;
    }

    public void setFuelDetail(List<FuelDetailModel> fuelDetail) {
        this.fuelDetail = fuelDetail;
    }
}
