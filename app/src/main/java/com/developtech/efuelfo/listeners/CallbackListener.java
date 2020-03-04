package com.developtech.efuelfo.listeners;

import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;

/**
 * Created by dt on 2/21/18.
 */

public interface CallbackListener {

    void onAddLocation(AddFuelStationRequestModel address);
}
