package com.developtech.efuelfo.model.requestModel;

import java.io.Serializable;

/**
 * Created by dt on 1/9/18.
 */

public class LocationRequestModel implements Serializable {
    public String latitude, longitude, driverId;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
