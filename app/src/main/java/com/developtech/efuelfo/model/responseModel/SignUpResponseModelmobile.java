package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;

public class SignUpResponseModelmobile implements Serializable {
    public String otpId;

    public String getId() {
        return otpId;
    }

    public void setId(String id) {
        this.otpId = id;
    }

}
