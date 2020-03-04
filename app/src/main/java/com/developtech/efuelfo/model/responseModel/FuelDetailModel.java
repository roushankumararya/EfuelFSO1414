package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;

/**
 * Created by dt on 2/7/18.
 */

public class FuelDetailModel implements Serializable
{
    String fuelType, price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
