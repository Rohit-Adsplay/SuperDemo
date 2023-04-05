package com.superdemo.code.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefersKYC {

    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public static String KYC_STATUS = "KYC_STATUS";

    public PrefersKYC(Context con) {
        this.context = con;
        prefs = con.getSharedPreferences("kycPrefersQNS", Context.MODE_PRIVATE);
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

    public void clearPrefersKYC(Context con) {
        this.context = con;
        prefs = con.getSharedPreferences("kycPrefersQNS", Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
