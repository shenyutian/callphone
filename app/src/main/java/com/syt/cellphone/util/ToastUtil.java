package com.syt.cellphone.util;

import android.widget.Toast;

import com.syt.cellphone.base.Config;
import com.syt.cellphone.base.MyApp;

/**
 * author：syt
 * Date: 2019-12-05
 * 作用: 简单通知
 */
public class ToastUtil {

    private final static boolean SWITCH = Config.toastSwitch;

    public static void makeText(String content) {
        if (SWITCH) {
            Toast.makeText(MyApp.getContext(), content, Toast.LENGTH_SHORT).show();
        }
    }
}
