package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 3/6/18.
 */

public class TransDetailRequestModel {
    String requestId;

    public TransDetailRequestModel(String requestId) {
        this.requestId = requestId;
    }

    public TransDetailRequestModel() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
