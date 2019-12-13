package com.syt.cellphone.base;

import android.app.Application;
import android.content.Context;

import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.util.SharedConfigUtil;

/**
 * @author：syt
 * Date: 2019-12-05
 * 作用:
 */
public class MyApp extends Application {

    /**
     * 个人登录信息 user
     */
    private static PhoneUser user;
    public  static Context   context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 配置保存初始化
        SharedConfigUtil.init(context);
    }

    /**
     * 全局context
     * @return 随时获取context
     */
    public static Context getContext() {
        return context;
    }


}
