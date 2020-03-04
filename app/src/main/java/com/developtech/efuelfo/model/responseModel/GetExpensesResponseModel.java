package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 2/5/18.
 */

public class GetExpensesResponseModel implements Serializable{
    AllVehicleResponseModel vehicle;
    List<ExpenseTypeModel> typeOfExpense;
    String vehicleOwner, date, odoMeter, reason, notes, createdAt, updatedAt, id;
    int totalOfExpense;

    public List<ExpenseTypeModel> getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(List<ExpenseTypeModel> typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public int getTotalOfExpense() {
        return totalOfExpense;
    }

    public void setTotalOfExpense(int totalOfExpense) {
        this.totalOfExpense = totalOfExpense;
    }

    public AllVehicleResponseModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(AllVehicleResponseModel vehicle) {
        this.vehicle = vehicle;
    }


    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
