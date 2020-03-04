package com.developtech.efuelfo.model.responseModel;

import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;

import java.io.Serializable;

/**
 * Created by dt on 3/17/18.
 */

public class VerifyVehicleModel implements Serializable{
    AllVehicleResponseModel vehicle;
    String fuelStation, name, mobileNumber, fuelType, quantity, amount, transactionId, createdBy, createdById, status,
            isDeleted, byOwner, pay4pal, createdAt, updatedAt, dispenseCode, id, driverName, vehicleOwnerName, pagination,
    vehicleNumber;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public AllVehicleResponseModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(AllVehicleResponseModel vehicle) {
        this.vehicle = vehicle;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getByOwner() {
        return byOwner;
    }

    public void setByOwner(String byOwner) {
        this.byOwner = byOwner;
    }

    public String getPay4pal() {
        return pay4pal;
    }

    public void setPay4pal(String pay4pal) {
        this.pay4pal = pay4pal;
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

    public String getDispenseCode() {
        return dispenseCode;
    }

    public void setDispenseCode(String dispenseCode) {
        this.dispenseCode = dispenseCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }
}
