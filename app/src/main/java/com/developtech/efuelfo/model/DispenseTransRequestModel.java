package com.developtech.efuelfo.model;

/**
 * Created by dt on 3/5/18.
 */

public class DispenseTransRequestModel
{
    String requestId, dispenseCode;

    public DispenseTransRequestModel(String requestId, String dispenseCode) {
        this.requestId = requestId;
        this.dispenseCode = dispenseCode;
    }

    public DispenseTransRequestModel(){

    }

    public String getDispenseCode() {
        return dispenseCode;
    }

    public void setDispenseCode(String dispenseCode) {
        this.dispenseCode = dispenseCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
