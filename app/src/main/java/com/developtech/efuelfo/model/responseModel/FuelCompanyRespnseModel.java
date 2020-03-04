package com.developtech.efuelfo.model.responseModel;

import java.util.List;

/**
 * Created by dt on 2/21/18.
 */

public class FuelCompanyRespnseModel {
    String image, name, id;
    List<String> fuelType;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getFuelType() {
        return fuelType;
    }

    public void setFuelType(List<String> fuelType) {
        this.fuelType = fuelType;
    }
}
