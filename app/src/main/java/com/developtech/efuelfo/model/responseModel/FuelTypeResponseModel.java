package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 1/10/18.
 */

public class FuelTypeResponseModel implements Serializable {
    @SerializedName("fuelStation")
    @Expose
    private String fuelStation;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("fuelType")
    @Expose
    private String fuelType;
    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("id")
    @Expose
    private String id;

    private List<FuelDetailModel> fuelDetail;

    public List<FuelDetailModel> getFuelDetail() {
        return fuelDetail;
    }

    public void setFuelDetail(List<FuelDetailModel> fuelDetail) {
        this.fuelDetail = fuelDetail;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
