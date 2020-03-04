package com.developtech.efuelfo.model.requestModel;

import java.util.List;

public class TankTypeUpdateRequest {
    List<TankTypeModel> tank;

    public TankTypeUpdateRequest(List<TankTypeModel> tank) {
        this.tank = tank;
    }
}
