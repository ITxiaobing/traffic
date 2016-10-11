package com.zksn.jilinjiaotong.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String getCurrentTime() {
        Date data = new Date();
        SimpleDateFormat  format=new SimpleDateFormat("HH:mm:ss");
        return  format.format(data);
    }
}


