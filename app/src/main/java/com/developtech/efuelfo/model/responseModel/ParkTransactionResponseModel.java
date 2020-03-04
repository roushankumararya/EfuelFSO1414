package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;

/**
 * Created by dt on 2/24/18.
 */

public class ParkTransactionResponseModel implements Serializable{
    AllVehicleResponseModel vehicle;
    GetFuelStationResponseModel fuelStation;
    String mobileNumber, fuelType, quantity, amount,mialage, status, createdBy, createdById, createdAt, updatedAt, id;
    boolean isDeleted, byOwner, pay4pal;
    String pagination;
    VehicleOwnerResponseModel vehicleOwner;

    public VehicleOwnerResponseModel getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(VehicleOwnerResponseModel vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getMialage() {
        return mialage;
    }

    public void setMialage(String mialage) {
        this.mialage = mialage;
    }

    public AllVehicleResponseModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(AllVehicleResponseModel vehicle) {
        this.vehicle = vehicle;
    }

    public GetFuelStationResponseModel getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(GetFuelStationResponseModel fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isByOwner() {
        return byOwner;
    }

    public void setByOwner(boolean byOwner) {
        this.byOwner = byOwner;
    }

    public boolean isPay4pal() {
        return pay4pal;
    }

    public void setPay4pal(boolean pay4pal) {
        this.pay4pal = pay4pal;
    }
}
