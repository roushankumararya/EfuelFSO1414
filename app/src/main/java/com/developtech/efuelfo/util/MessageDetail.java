package com.developtech.efuelfo.util;

import java.io.Serializable;

public class MessageDetail implements Serializable{
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    String otp;


}