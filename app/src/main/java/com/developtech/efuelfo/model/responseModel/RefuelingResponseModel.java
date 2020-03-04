package com.developtech.efuelfo.model.responseModel;

import java.util.List;

public class RefuelingResponseModel {
    float totalVol, totalAmount;
    List<RefuelingModel> refuel;
    int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public float getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(float totalVol) {
        this.totalVol = totalVol;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<RefuelingModel> getRefuel() {
        return refuel;
    }

    public void setRefuel(List<RefuelingModel> refuel) {
        this.refuel = refuel;
    }
}
