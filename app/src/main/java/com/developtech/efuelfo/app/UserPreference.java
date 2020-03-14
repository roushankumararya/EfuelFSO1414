package com.developtech.efuelfo.app;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    Context context;
    SharedPreferences sharedPreferences;
    public static UserPreference userpref;

    public static UserPreference getInstance(Context context) {
        if (userpref == null) {
            userpref = new UserPreference(context.getApplicationContext());
        }
        return userpref;
    }

    public UserPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Efule", context.MODE_PRIVATE);
    }

    public void setNumber(String number) {
        sharedPreferences.edit().putString("userId", number).commit();

    }

    public String getNumber() {
        return sharedPreferences.getString("userId", "");
    }

    public void setIsLogin(boolean isLogin) {
        sharedPreferences.edit().putBoolean("setIsLogin", isLogin).commit();

    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean("setIsLogin", false);
    }
    public void setFirstname(String fname) {
        sharedPreferences.edit().putString("setFirstname", fname).commit();

    }

    public String getFirsName() {
        return sharedPreferences.getString("setFirstname", "");
    }
    public void setLastname(String lname) {
        sharedPreferences.edit().putString("setLastname", lname).commit();

    }

    public String getLastName() {
        return sharedPreferences.getString("setLastname", "");
    }
    public void setEmailId(String emailId) {
        sharedPreferences.edit().putString("setEmailId", emailId).commit();

    }

    public String getEmailId() {
        return sharedPreferences.getString("setEmailId", "");
    }


    public void setAddress(String address) {
        sharedPreferences.edit().putString("setAddress", address).commit();
    }
    public String getAddress() {
        return sharedPreferences.getString("setAddress", "");
    }

    public void setAddresstwo(String address) {
        sharedPreferences.edit().putString("setAddresstwo", address).commit();
    }
    public String getAddresstwo() {
        return sharedPreferences.getString("setAddresstwo", "");
    }



}
