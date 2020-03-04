package com.developtech.efuelfo.model.requestModel;

public class ReportRequestModel {
    String startDate, endDate, fuelStationId;

    public ReportRequestModel(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReportRequestModel()
    {
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
