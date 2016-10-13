package com.zksn.jilinjiaotong.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by admin on 2016/10/13.
 */

public class ToastUtils {

    public static void setShowTop(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);// 最上方显示
        toast.show();
    }
}
