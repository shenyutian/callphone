package com.syt.cellphone.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author：syt Date: 2019-12-11
 * 使用前应该初始化
 * 作用: 长久保存app的配置信息
 */
public class SharedConfigUtil {

    private static final String TAG = SharedConfigUtil.class.getName();
    private static SharedPreferences.Editor editor;
    private static SharedPreferences preferences;

    /**
     * 必须执行的操作，初始化
     * @param context 上下文
     */
    @SuppressLint("CommitPrefEdits")
    public static void init(Context context) {
        preferences = context.getSharedPreferences("settingParam", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存 是否开启夜间模式
     */
    public static void saveNightOnOff(boolean onOff) {
        if (editor != null) {
            editor.putBoolean("nightOnOff", onOff);
            editor.commit();
        }
    }

    /**
     * 夜间模式 开关
     * @return 开关 true 开启 flase 关闭
     */
    public static boolean getNightOnOff() {
        if (preferences != null) {
            return preferences.getBoolean("nightOnOff", false);
        }
        return false;
    }

    /**
     * 保存底部菜单选项
     */
    public static void saveBottomMenus(int menus) {
        if (editor != null) {
            editor.putInt("bottomMenus", menus);
            editor.commit();
        }
    }

    /**
     * 获取底部菜单选项
     * @return 1-4
     */
    public static int getBottomMenus() {
        if (preferences != null) {
            return preferences.getInt("bottomMenus", 1);
        }
        return 1;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public static String getUserName() {
        if (preferences != null) {
            return preferences.getString("userName", "");
        }
        return "";
    }

    /**
     * 保存用户名
     * @param userName 用户名
     */
    public static void saveUserName(String userName) {
        if (editor != null) {
            editor.putString("userName", userName);
            editor.commit();
        }
    }

    /**
     *  获取token
     */
    public static String getToken() {
        if (preferences != null) {
            return preferences.getString("token", "");
        }
        return "";
    }

    /**
     * 保存token
     */
    public static void saveToken(String token) {
        if (editor != null) {
            editor.putString("token", token);
            editor.commit();
        }
    }
}
