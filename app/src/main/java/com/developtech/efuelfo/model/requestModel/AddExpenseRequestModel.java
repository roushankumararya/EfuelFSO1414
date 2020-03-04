package com.developtech.efuelfo.model.requestModel;

import com.developtech.efuelfo.model.responseModel.ExpenseTypeModel;

import java.util.List;

/**
 * Created by developtech on 2/3/18.
 */

public class AddExpenseRequestModel {
    String vehicle, date, odoMeter, reason, notes, requestId;
    List<ExpenseTypeModel> typeOfExpense;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ExpenseTypeModel> getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(List<ExpenseTypeModel> typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }
}
