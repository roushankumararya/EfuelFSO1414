package com.developtech.efuelfo.model.requestModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dt on 1/9/18.
 */

public class OperatorsResponseModel implements Serializable {
    public String firstName, lastName, mobileNumber, email, loginId, userType, mobileVerified, id, image, countryCode;
    private boolean isManager, isBlocked;
    private ArrayList<String> blockedFuelStation;

    public ArrayList<String> getBlockedFuelStation() {
        if (blockedFuelStation == null) {
            blockedFuelStation = new ArrayList<>();
        }
        return blockedFuelStation;
    }

    public void setBlockedFuelStation(ArrayList<String> blockedFuelStation) {

        this.blockedFuelStation = blockedFuelStation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(String mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
