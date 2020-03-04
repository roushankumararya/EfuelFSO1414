package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dt on 1/10/18.
 */

public class CreditAgreementResponseModel implements Serializable {
    @SerializedName("vehicleOwner")
    @Expose
    private VehicleOwnerResponseModel vehicleOwner;
    @SerializedName("fuelStation")
    @Expose
    private String fuelStation;
    @SerializedName("creditLimit")
    @Expose
    private int creditLimit;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("remainingCredits")
    @Expose
    private float remainingCredits;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("paymentStatus")
    @Expose
    private String paymentStatus;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("isTerminated")
    @Expose
    private boolean isTerminated;

    @SerializedName("id")
    @Expose
    private String id;

    String pagination;

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public VehicleOwnerResponseModel getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(VehicleOwnerResponseModel vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRemainingCredits() {
        return remainingCredits;
    }

    public void setRemainingCredits(float remainingCredits) {
        this.remainingCredits = remainingCredits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
