package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 1/10/18.
 */

public class GetFavoriteStationResponseModel implements Serializable{

    private List<FuelStationResponseModel> favourite;

    public List<FuelStationResponseModel> getFavourite() {
        return favourite;
    }

    public void setFavourite(List<FuelStationResponseModel> favourite) {
        this.favourite = favourite;
    }
}
