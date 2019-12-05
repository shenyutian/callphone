package com.syt.cellphone.util;

import android.content.Context;
import android.widget.Switch;
import android.widget.Toast;

import com.syt.cellphone.base.Config;

/**
 * author：syt
 * Date: 2019-12-05
 * 作用: 简单通知
 */
public class ToastUtil {

    private final static boolean SWITCH = Config.toastSwitch;

    public static void makeText(Context context, String content) {
        if (SWITCH) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }
}
