package com.developtech.efuelfo.model.requestModel;

public class SwitchCreditAgreementReqModel {
    boolean isCreditAgreement;
    String fuelStationId;

    public boolean isCreditAgreement() {
        return isCreditAgreement;
    }

    public void setCreditAgreement(boolean creditAgreement) {
        isCreditAgreement = creditAgreement;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }
}
