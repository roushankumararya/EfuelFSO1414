package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 2/6/18.
 */

public class ResendOtpRequestModel {
    String otpId, resendCase;

    public String getOtpId() {
        return otpId;
    }

    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }

    public String getResendCase() {
        return resendCase;
    }

    public void setResendCase(String resendCase) {
        this.resendCase = resendCase;
    }
}
