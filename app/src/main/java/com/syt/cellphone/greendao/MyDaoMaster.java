package com.syt.cellphone.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * @author：syt Date: 2019-12-17
 * 作用: 数据库管理 升级
 */
public class MyDaoMaster extends DaoMaster.OpenHelper {

    private static final String TAG = "MyDaoMaster";

    public MyDaoMaster(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
//        Mig
    }
}
