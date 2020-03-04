package com.developtech.efuelfo.model.responseModel;

import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;

import java.io.Serializable;

/**
 * Created by dt on 3/13/18.
 */

public class VerifyTransResponseModel implements Serializable{
    String fuelStation, amount, fuelQuantity, fuelPrice, convenienceFee, gst, paymentType, totalAmount, id, vehicleOwner;
    AddRefuelRequestModel refuelRequest;

    public AddRefuelRequestModel getRefuelRequest() {
        return refuelRequest;
    }

    public void setRefuelRequest(AddRefuelRequestModel refuelRequest) {
        this.refuelRequest = refuelRequest;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(String fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public String getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(String fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public String getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }
}
