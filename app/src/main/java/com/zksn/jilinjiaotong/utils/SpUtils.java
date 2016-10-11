package com.zksn.jilinjiaotong.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {

    private static String CITY = "city";
    private static String current_city = "currentCity";

    public static void setCurrentCity(Context context, String currentCity) {
        SharedPreferences sp = context.getSharedPreferences(CITY, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(current_city, currentCity);
        edit.commit();

    }

    public static String getCurrentCity(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CITY, Context.MODE_PRIVATE);
        return sp.getString(current_city, "");

    }
}
