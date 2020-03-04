package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dt on 1/10/18.
 */

public class AddFuelRequestResponseModel {
    @SerializedName("vehicle")
    @Expose
    private String vehicle;
    @SerializedName("fuelType")
    @Expose
    private String fuelType;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("mialage")
    @Expose
    private int mialage;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdById")
    @Expose
    private String createdById;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("byOwner")
    @Expose
    private boolean byOwner;
    @SerializedName("pay4pal")
    @Expose
    private boolean pay4pal;
    @SerializedName("id")
    @Expose
    private String id;

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
