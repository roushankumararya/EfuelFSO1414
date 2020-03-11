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
}
