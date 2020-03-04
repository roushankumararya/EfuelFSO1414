package com.developtech.efuelfo.model.responseModel.PlacesApiModels;

/**
 * Created by Happy on 11/5/2016.
 */

public class Location
{
    double lat;

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    double lng;
}
