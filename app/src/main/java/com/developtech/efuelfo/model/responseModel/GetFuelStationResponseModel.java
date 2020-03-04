package com.developtech.efuelfo.model.responseModel;

import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dt on 1/10/18.
 */

public class GetFuelStationResponseModel implements Serializable {

    @SerializedName("stationOwner")
    @Expose
    private SignInResponseModel stationOwner;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dealerCode")
    @Expose
    private String dealerCode;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;
    @SerializedName("landlineNumber")
    @Expose
    private String landlineNumber;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("fuelStationId")
    @Expose
    private String fuelStationId;
    @SerializedName("isOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("availability")
    @Expose
    private Boolean availability;
    @SerializedName("isFuelStationVerified")
    @Expose
    private boolean isFuelStationVerified;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("id")
    @Expose
    private String id;

    private String image, countryCode, country, state, city;

    @SerializedName("fuelCompany")
    @Expose
    private FuelCompanyRespnseModel fuelCompany;

    private boolean isCreditAgreement;

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isCreditAgreement() {
        return isCreditAgreement;
    }

    public boolean getFuelStationVerified() {
        return isFuelStationVerified;
    }

    public void setFuelStationVerified(boolean fuelStationVerified) {
        isFuelStationVerified = fuelStationVerified;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreditAgreement(boolean creditAgreement) {
        isCreditAgreement = creditAgreement;
    }

    List<OperatorsResponseModel> operator;

    public List<OperatorsResponseModel> getOperator() {
        return operator;
    }

    public void setOperator(List<OperatorsResponseModel> operator) {
        this.operator = operator;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public FuelCompanyRespnseModel getFuelCompany() {
        return fuelCompany;
    }

    public void setFuelCompany(FuelCompanyRespnseModel fuelCompany) {
        this.fuelCompany = fuelCompany;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SignInResponseModel getStationOwner() {
        return stationOwner;
    }

    public void setStationOwner(SignInResponseModel stationOwner) {
        this.stationOwner = stationOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
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

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
