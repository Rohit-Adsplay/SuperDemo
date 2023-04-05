package com.superdemo.code.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefersPublic {

    Context context;
    SharedPreferences prefs;
    public static String PrivacyLink = "PrivacyLink";
    public static String TermsLink = "TermsLink";
    public static String ContactLink = "ContactLink";
    public static String ReferDescription = "ReferDescription";
    public static String ReferAmount = "ReferAmount";
    public static String KYCPoints = "KYCPoints";
    public static String PhotoInstructions = "PhotoInstructions";
    public static String FCMTOKEN = "FCMTOKEN";
    public static String UserLoggedIN = "UserLoggedIN";
    public static String WarningMessage = "WarningMessage";
    public static String delay_cost_percent = "delay_cost_percent";

    public PrefersPublic(Context con) {
        this.context = con;
        prefs = con.getSharedPreferences("publicPrefersQNS", Context.MODE_PRIVATE);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value).apply();
    }

    public String getString(String key) {
        return prefs.getString(key, "");
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value).apply();
    }

    public boolean getBooleanData(String key) {
        return prefs.getBoolean(key, false);
    }
}
