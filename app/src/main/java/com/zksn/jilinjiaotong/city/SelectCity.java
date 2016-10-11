package com.zksn.jilinjiaotong.city;


import android.content.Context;

import com.zksn.jilinjiaotong.utils.SpUtils;

public class SelectCity {

    public static void setSelectCity(Context mContext, int position) {
        switch (position) {
            case 0:
                SpUtils.setCurrentCity(mContext, "54161");
                break;
            case 1:
                SpUtils.setCurrentCity(mContext, "54156");
                break;
            case 2:
                SpUtils.setCurrentCity(mContext, "54065");
                break;
            case 3:
                SpUtils.setCurrentCity(mContext, "54063");
                break;
            case 4:
                SpUtils.setCurrentCity(mContext, "54172");
                break;
            case 5:
                SpUtils.setCurrentCity(mContext, "54069");
                break;
            case 6:
                SpUtils.setCurrentCity(mContext, "54181");
                break;
            case 7:
                SpUtils.setCurrentCity(mContext, "54186");
                break;
            case 8:
                SpUtils.setCurrentCity(mContext, "54187");
                break;
            case 9:
                SpUtils.setCurrentCity(mContext, "54290");
                break;
            case 10:
                SpUtils.setCurrentCity(mContext, "54292");
                break;
            case 11:
                SpUtils.setCurrentCity(mContext, "54293");
                break;
        }

    }

    public static String getSelectCity( String code) {

        if (code.equals("54161")) {
            return "长春";
        } else if (code.equals("54156")) {
            return "公主岭";
        } else if (code.equals("54065")) {
            return "德惠";
        } else if (code.equals("54063")) {
            return "扶余";
        } else if (code.equals("54172")) {
            return "吉林";
        } else if (code.equals("54069")) {
            return "九台";
        } else if (code.equals("54181")) {
            return "蛟河";
        } else if (code.equals("54186")) {
            return "敦化";
        } else if (code.equals("54187")) {
            return "安图";
        } else if (code.equals("54290")) {
            return "龙井";
        } else if (code.equals("54292")) {
            return "延吉";
        } else if (code.equals("54293")) {
            return "图们";
        } else {
            return "长春";
        }
    }
}
