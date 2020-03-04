package com.developtech.efuelfo.model.responseModel;

/**
 * Created by dt on 2/26/18.
 */

public class SaleFuelTypeResponseModel {
    String vehicle, fuelStation, fuelType, createdBy, createdById, status, createdAt, updatedAt, id;
    int quantity, amount, mialage;
    double price;

    boolean isDeleted, byOwner, pay4pal;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMialage() {
        return mialage;
    }

    public void setMialage(int mialage) {
        this.mialage = mialage;
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
