package com.developtech.efuelfo.model.requestModel;

import com.developtech.efuelfo.model.responseModel.ExpenseTypeModel;

import java.util.List;

/**
 * Created by dt on 2/5/18.
 */

public class AddServiceRequestModel {
    String vehicle, date, odoMeter, notes, requestId;
    List<ExpenseTypeModel> typeOfService;

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOdoMeter() {
        return odoMeter;
    }

    public void setOdoMeter(String odoMeter) {
        this.odoMeter = odoMeter;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<ExpenseTypeModel> getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(List<ExpenseTypeModel> typeOfService) {
        this.typeOfService = typeOfService;
    }
}
