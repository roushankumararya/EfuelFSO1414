package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 2/5/18.
 */

public class GetServiceResponseModel implements Serializable{
    AllVehicleResponseModel vehicle;
    List<ExpenseTypeModel> typeOfService;
    String vehicleOwner, date, odoMeter,notes, createdAt, updatedAt, id;
    int totalOfService;

    public AllVehicleResponseModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(AllVehicleResponseModel vehicle) {
        this.vehicle = vehicle;
    }

    public List<ExpenseTypeModel> getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(List<ExpenseTypeModel> typeOfService) {
        this.typeOfService = typeOfService;
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

    public int getTotalOfService() {
        return totalOfService;
    }

    public void setTotalOfService(int totalOfService) {
        this.totalOfService = totalOfService;
    }
}
