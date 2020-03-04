package com.developtech.efuelfo.model.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 1/10/18.
 */

public class SignInResponseModel implements Serializable {

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
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("loginId")
    @Expose
    private String loginId;
    @SerializedName("mobileVerified")
    @Expose
    private Boolean mobileVerified;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("vehicleOwners")
    @Expose
    private List<VehicleOwnerResponseModel> vehicleOwners;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("altMobileNumber")
    @Expose
    private String altMobile;

    private List<String> fuelStation;
    private List<GetFuelStationResponseModel> fuelStations;

    public List<GetFuelStationResponseModel> getFuelStations() {
        return fuelStations;
    }

    public void setFuelStations(List<GetFuelStationResponseModel> fuelStations) {
        this.fuelStations = fuelStations;
    }

    public List<String> getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(List<String> fuelStation) {
        this.fuelStation = fuelStation;
    }



    public String getAltMobile() {
        return altMobile;
    }

    public void setAltMobile(String altMobile) {
        this.altMobile = altMobile;
    }

    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<VehicleOwnerResponseModel> getVehicleOwners() {
        return vehicleOwners;
    }

    public void setVehicleOwners(List<VehicleOwnerResponseModel> vehicleOwners) {
        this.vehicleOwners = vehicleOwners;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
