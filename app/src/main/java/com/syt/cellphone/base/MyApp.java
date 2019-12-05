package com.syt.cellphone.base;

import android.app.Application;
import com.syt.cellphone.pojo.PhoneUser;

import org.litepal.LitePal;

import java.util.concurrent.ThreadFactory;

/**
 * author：syt
 * Date: 2019-12-05
 * 作用:
 */
public class MyApp extends Application {

    private static MyApp myApp;
    // 个人登录信息
    private static PhoneUser user;

    /**
     *     单例模式来创建 BaseApplication
      */
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

    /**
     *     防止其他创建
      */
    public MyApp() {
        throw new RuntimeException();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 建立SqLite
        LitePal.initialize(this);
    }

}
