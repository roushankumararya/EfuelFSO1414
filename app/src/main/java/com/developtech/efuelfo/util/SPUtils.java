package com.developtech.efuelfo.util;

import android.content.SharedPreferences;

import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by android on 9/5/17.
 */

public class SPUtils {

    private SharedPreferences sharedPreferences;
    private String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    private String VEHICLE_OWNER_RESPONSE_LIST = "VEHICLE_OWNER_RESPONSE_LIST";
    private String FUEL_STATIONS_RESPONSE_LIST = "FUEL_STATIONS_RESPONSE_LIST";
    private String VEHICLE_OWNER_RESPONSE = "VEHICLE_OWNER_RESPONSE";
    private String FUEL_STATION_MODEL = "FUEL_STATION_RESPONSE";
    private String KEPP_ME_LOGIN = "KEPP_ME_LOGIN";
    private String MANAGER_CHANGED = "MANAGER_CHANGED";
    private String DELETED = "DELETED";
    private String NOTI_MSG = "NOTI_MSG";
    private String TOKEN = "TOKEN";
    private String USER_ID = "USER_ID";
    private String FIRST_NAME = "FIRST_NAME";
    private String LAST_NAME = "LAST_NAME";
    private String EMAIL = "EMAIL";
    private String MOBILE_NUMBER = "MOBILE_NUMBER";
    private String PINCODE = "PINCODE";
    private String ADDRESS = "ADDRESS";
    private String COUNTRY = "COUNTRY";
    private String USER_DATA = "user_login";
    private String LAT = "latitude";
    private String LNG = "lng";

    @Inject
    public SPUtils(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveAccountType(ACCOUNT_TYPES type) {
        sharedPreferences.edit().putString(ACCOUNT_TYPE, type.toString()).apply();
    }

    public void saveUserData(SignInResponseModel userData) {
        Gson myObj = new Gson();
        String json = myObj.toJson(userData);
        sharedPreferences.edit().putString(USER_DATA, json).apply();
    }

    public void saveVehicleOwnerResponse(List<VehicleOwnerResponseModel> list) {
        Gson myObj = new Gson();
        String json = myObj.toJson(list);
        sharedPreferences.edit().putString(VEHICLE_OWNER_RESPONSE_LIST, json).apply();
    }

    public List<VehicleOwnerResponseModel> getVehicleOwnerResponse() {
        Gson myObj = new Gson();
        String response = sharedPreferences.getString(VEHICLE_OWNER_RESPONSE_LIST, "");
        if (response.isEmpty())
            return null;

        return myObj.fromJson(response, new TypeToken<List<VehicleOwnerResponseModel>>() {
        }.getType());
    }

    public void saveFuelStationsList(List<GetFuelStationResponseModel> statinsList) {
        Gson myObj = new Gson();
        String json = myObj.toJson(statinsList);
        sharedPreferences.edit().putString(FUEL_STATIONS_RESPONSE_LIST, json).apply();
    }

    public List<GetFuelStationResponseModel> getAllFuelStationsList() {
        Gson myObj = new Gson();
        String response = sharedPreferences.getString(FUEL_STATIONS_RESPONSE_LIST, "");
        if (response.isEmpty())
            return null;

        return myObj.fromJson(response, new TypeToken<List<GetFuelStationResponseModel>>() {
        }.getType());
    }

    public void saveVehicleOwnerResponse(VehicleOwnerResponseModel model) {
        Gson myObj = new Gson();
        String json = myObj.toJson(model);
        sharedPreferences.edit().putString(VEHICLE_OWNER_RESPONSE, json).apply();
    }

    public void saveFuelStation(GetFuelStationResponseModel model) {
        Gson myObj = new Gson();
        String json = myObj.toJson(model);
        sharedPreferences.edit().putString(FUEL_STATION_MODEL, json).apply();
    }


    public GetFuelStationResponseModel getFuelStationModel() {
        Gson myObj = new Gson();
        String response = sharedPreferences.getString(FUEL_STATION_MODEL, "");
        if (response.isEmpty())
            return null;
            return myObj.fromJson(response, GetFuelStationResponseModel.class);
    }


    public VehicleOwnerResponseModel getVehicleOwnerResponseModel() {
        Gson myObj = new Gson();
        String response = sharedPreferences.getString(VEHICLE_OWNER_RESPONSE, "");
        if (response.isEmpty())
            return null;

        return myObj.fromJson(response, VehicleOwnerResponseModel.class);
    }


    public SignInResponseModel getUserData() {
        Gson myObj = new Gson();
        String response = sharedPreferences.getString(USER_DATA, "");

        if (response.isEmpty())
            return null;

        return myObj.fromJson(response, SignInResponseModel.class);
    }

    public void saveToken(String token) {
        SignInResponseModel model = getUserData();
        if (model == null)
            model = new SignInResponseModel();
        model.setToken(token);
        saveUserData(model);
    }

    public void saveCurrentLatLng(String lat, String lng) {
        sharedPreferences.edit().putString(LAT, lat).putString(LNG, lng).apply();
        ;
    }

    public String getLat() {
        return sharedPreferences.getString(LAT, "");
    }

    public String getLng() {
        return sharedPreferences.getString(LNG, "");
    }

    public void clearUserData() {
        sharedPreferences.edit().putString(USER_DATA, "").apply();
        sharedPreferences.edit().putString(FUEL_STATION_MODEL, "").apply();
        sharedPreferences.edit().putString(FUEL_STATIONS_RESPONSE_LIST, "").apply();
    }

    public String getUserId() {
        return getUserData() == null ? "" : getUserData().getId();
    }

    public String getToken() {
        return getUserData() == null ? "" : getUserData().getToken();
    }

    public String getName() {
        return getUserData() == null ? "" : getUserData().getFirstName() + " " + getUserData().getLastName();
    }


    public void saveFirebaseToken(String token) {
        sharedPreferences.edit().putString("firebase_token", token).apply();
    }

    public Boolean isRegistered() {
        return sharedPreferences.getBoolean("is_registered", false);
    }


    public String getFirebaseToken() {
        return sharedPreferences.getString("firebase_token", "");
    }

    public void saveIsRegistered(Boolean isRegistered) {
        sharedPreferences.edit().putBoolean("is_registered", isRegistered).apply();
    }


    public String getMobileNo() {
        return getUserData() == null ? "" : getUserData().getMobileNumber();
    }

    public String getEmail() {
        return getUserData() == null ? "" : getUserData().getEmail();
    }

    public ACCOUNT_TYPES getAccountType() {
        String sharedType = sharedPreferences.getString(ACCOUNT_TYPE,"");
        ACCOUNT_TYPES type =null;
        if (!sharedType.trim().isEmpty()) {
            type = ACCOUNT_TYPES.valueOf(sharedType);
        } else {
            return null;
        }
        return type;
    }

    public enum ACCOUNT_TYPES {
        DRV, VCO, FSO, OPR
    }

    public enum API_CODES {
        OK, FAIL;
    }

    public void setKeepMeLogin(boolean keepMeLogin) {
        sharedPreferences.edit().putBoolean(KEPP_ME_LOGIN, keepMeLogin).apply();
    }

    public boolean isKeepMeLogin() {
        return sharedPreferences.getBoolean(KEPP_ME_LOGIN, false);
    }

    public void saveIsDeleted(boolean isDeleted) {
        sharedPreferences.edit().putBoolean(DELETED, isDeleted).apply();
    }

    public boolean isDeleted() {
        return sharedPreferences.getBoolean(DELETED, false);
    }

    public void saveNotiMsg(String msg) {
        sharedPreferences.edit().putString(NOTI_MSG, msg).apply();
    }

    public String getNotiMsg() {
        return sharedPreferences.getString(NOTI_MSG, "");
    }

    public void saveManagerChanged(boolean isManagerChanged) {
        sharedPreferences.edit().putBoolean(MANAGER_CHANGED, isManagerChanged).apply();
    }

    public boolean getIsManagerChanged() {
        return sharedPreferences.getBoolean(MANAGER_CHANGED, false);
    }

    public boolean isManager() {
        boolean isOpratorManager = false;
        List<OperatorsResponseModel> oprList = getFuelStationModel().getOperator();
        if (oprList != null && oprList.size() > 0) {
            for (OperatorsResponseModel model : oprList) {
                if (getUserData().getId().equals(model.getId())) {
                    if (model.isManager()) {
                        isOpratorManager = true;

                    } else {
                        isOpratorManager = false;
                    }
                }
            }
        }
        return isOpratorManager;
    }

}
