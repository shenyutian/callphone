package com.syt.cellphone.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.syt.cellphone.greendao.DaoMaster;
import com.syt.cellphone.greendao.DaoSession;
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
    public  static   PhoneUser    user;
    public  static   Context      context;
    private static   DaoSession   daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 配置保存初始化
        SharedConfigUtil.init(context);
        // greenDao 初始化
        initGrennDao();
    }

    /**
     * 全局context
     * @return 随时获取context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * greenDao初始化
     */
    private void initGrennDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cellphone.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
