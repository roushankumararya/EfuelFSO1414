package com.developtech.efuelfo.model.requestModel;

import java.io.Serializable;

/**
 * Created by dt on 1/9/18.
 */

public class TerminateAgreementRequestModel implements Serializable {
    public String agreementId;

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }
}
