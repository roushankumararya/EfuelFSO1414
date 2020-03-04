package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 3/22/18.
 */

public class FindOperatorRequestModel {
    String mobileNumber, fuelStationId;

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public FindOperatorRequestModel(){}

    public FindOperatorRequestModel(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
