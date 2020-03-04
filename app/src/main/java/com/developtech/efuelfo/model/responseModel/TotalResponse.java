package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalResponse {

@SerializedName("convenienceFee")
@Expose
private Float convenienceFee;
@SerializedName("id")
@Expose
private String id;
@SerializedName("gst")
@Expose
private Float gst;
@SerializedName("amount")
@Expose
private Float amount;
@SerializedName("quantity")
@Expose
private Float quantity;
@SerializedName("fuelPrice")
@Expose
private Float fuelPrice;
@SerializedName("totalAmount")
@Expose
private Float totalAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getConvenienceFee() {
return convenienceFee;
}

public void setConvenienceFee(Float convenienceFee) {
this.convenienceFee = convenienceFee;
}

public Float getGst() {
return gst;
}

public void setGst(Float gst) {
this.gst = gst;
}

public Float getQuantity() {
return quantity;
}

public void setQuantity(Float quantity) {
this.quantity = quantity;
}

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(Float fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }
}