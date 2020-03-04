package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dt on 1/10/18.
 */

public class FuelStationByQRResponseModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("schedules")
    @Expose
    private List<FuelTypeResponseModel> schedules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FuelTypeResponseModel> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<FuelTypeResponseModel> schedules) {
        this.schedules = schedules;
    }
}
