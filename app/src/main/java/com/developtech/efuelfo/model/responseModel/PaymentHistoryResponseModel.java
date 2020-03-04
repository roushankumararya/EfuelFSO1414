package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dt on 1/10/18.
 */

public class PaymentHistoryResponseModel implements Serializable {
    @SerializedName("vehicle")
    @Expose
    private AllVehicleResponseModel vehicle;
    @SerializedName("fuelStation")
    @Expose
    private FuelStationResponseModel fuelStation;
    @SerializedName("driver")
    @Expose
    private DriverResponseModel driver;

    VehicleOwnerResponseModel vehicleOwner;

    @SerializedName("amount")
    @Expose
    private float amount;
    @SerializedName("createdById")
    @Expose
    private String createdById;
    @SerializedName("fuelType")
    @Expose
    private String fuelType;
    @SerializedName("mialage")
    @Expose
    private String mialage;
    @SerializedName("quantity")
    @Expose
    private float quantity;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
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
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private String id;

    String gst, convenienceFee, totalAmount, invoiceStatus;

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public VehicleOwnerResponseModel getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(VehicleOwnerResponseModel vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getMialage() {
        return mialage;
    }

    public void setMialage(String mialage) {
        this.mialage = mialage;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DriverResponseModel getDriver() {
        return driver;
    }

    public void setDriver(DriverResponseModel driver) {
        this.driver = driver;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public AllVehicleResponseModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(AllVehicleResponseModel vehicle) {
        this.vehicle = vehicle;
    }

    public FuelStationResponseModel getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStationResponseModel fuelStation) {
        this.fuelStation = fuelStation;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
