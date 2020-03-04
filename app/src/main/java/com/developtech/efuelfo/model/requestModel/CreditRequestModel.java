package com.developtech.efuelfo.model.requestModel;

import java.io.Serializable;

/**
 * Created by dt on 1/9/18.
 */

public class CreditRequestModel implements Serializable {
    public String creditLimit, duration, fuelStation, remainingCredits;

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getRemainingCredits() {
        return remainingCredits;
    }

    public void setRemainingCredits(String remainingCredits) {
        this.remainingCredits = remainingCredits;
    }
}
