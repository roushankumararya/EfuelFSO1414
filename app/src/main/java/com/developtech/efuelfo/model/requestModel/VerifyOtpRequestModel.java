package com.developtech.efuelfo.model.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dt on 1/9/18.
 */

public class VerifyOtpRequestModel {
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("otpId")
    @Expose
    private String otpId;
    @SerializedName("userType")
    @Expose
    private String userType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = "";
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email ="";
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = "";
    }

//    public String getPincode() {
//        return pincode;
//    }
//
//    public void setPincode(String pincode) {
//        this.pincode = pincode;
//    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpId() {
        return otpId;
    }

    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
