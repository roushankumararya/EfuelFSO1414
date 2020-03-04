package com.developtech.efuelfo.model.requestModel;

public class TankTypeModel {
    String id;
    float OFQty, CFQty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getOFQty() {
        return OFQty;
    }

    public void setOFQty(float OFQty) {
        this.OFQty = OFQty;
    }

    public float getCFQty() {
        return CFQty;
    }

    public void setCFQty(float CFQty) {
        this.CFQty = CFQty;
    }
}
