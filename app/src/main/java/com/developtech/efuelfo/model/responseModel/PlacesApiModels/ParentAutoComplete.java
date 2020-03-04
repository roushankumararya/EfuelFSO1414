package com.developtech.efuelfo.model.responseModel.PlacesApiModels;

import java.util.List;


public class ParentAutoComplete
{
    List<Predictions> predictions;
    String status;

    public List<Predictions> getPredictions()
    {
        return predictions;
    }

    public void setPredictions(List<Predictions> predictions)
    {
        this.predictions = predictions;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
