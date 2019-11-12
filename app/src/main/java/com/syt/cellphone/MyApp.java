package com.syt.cellphone;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.util.LogUtil;

import org.litepal.LitePal;

import java.util.concurrent.ThreadFactory;

public class MyApp extends Application {

    private static  MyApp myApp;
    // 个人登录信息
    private static PhoneUser user;

    // 单例模式来创建 获取myApp
    public static MyApp getInstance() {
        if (myApp == null) {
            synchronized (MyApp.class) {
                if (myApp == null) {
                    myApp = new MyApp();
                }
            }
        }
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 建立SqLite
        LitePal.initialize(this);
    }

    private ThreadFactory threadFactory;

}
