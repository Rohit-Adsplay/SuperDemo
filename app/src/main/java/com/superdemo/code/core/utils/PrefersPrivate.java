package com.superdemo.code.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefersPrivate {

    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static String UUID = "UUID";
    public static String USERNAME = "USERNAME";
    public static String FULLNAME = "FULLNAME";
    public static String PROFILE_IMG = "PROFILE_IMG";
    public static String EMAIL = "EMAIL";
    public static String KYC_STATUS = "KYC_STATUS";
    public static String PHONE = "PHONE";
    public static String DEVICEID = "DEVICEID";
    public static String REFERAL_ID = "REFERAL_ID";
    public static String REFERED_BY = "REFERED_BY";
    public static String TempReferID = "TempReferID"; // during Signup

    public PrefersPrivate(Context con) {
        this.context = con;
        prefs = con.getSharedPreferences("privatePrefersQNS", Context.MODE_PRIVATE);
    }

    public void setString(String key, String value) {
        editor = prefs.edit();
        editor.putString(key, value).apply();
    }

    public String getString(String key) {
        return prefs.getString(key, "");
    }

    public void setBoolean(String key, boolean value) {
        editor = prefs.edit();
        editor.putBoolean(key, value).apply();
    }

    public boolean getBooleanData(String key) {
        return prefs.getBoolean(key, false);
    }

    public void clearPrefers(Context con) {
        this.context = con;
        prefs = con.getSharedPreferences("privatePrefersQNS", Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
