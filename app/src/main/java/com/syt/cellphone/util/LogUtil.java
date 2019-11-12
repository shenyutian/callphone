package com.syt.cellphone.util;

import android.util.Log;

/**
 *  日志工具类
 */
public class LogUtil {

    private final static String TAG = "syt";

    private final static int VERBOSE = 1;
    private final static int DEBUG = 2;
    private final static int IINFO= 3;
    private final static int ERROR = 4;
    private final static int NATHING = 5;
    // 设置日志级别
    private final static int level = ERROR;

    public static void v(String msg) {
        if (level >= VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (level >= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (level >= IINFO) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (level >= ERROR) {
            Log.e(TAG, msg);
        }
    }
}
