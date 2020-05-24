package com.syt.cellphone.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.syt.cellphone.greendao.DaoMaster;
import com.syt.cellphone.greendao.DaoSession;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.ui.SytMainActivity;
import com.syt.cellphone.util.SharedConfigUtil;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import java.io.File;

/**
 * @author：syt
 * Date: 2019-12-05
 * 作用:
 */
public class MyApp extends Application {

    /**
     * 个人登录信息 user
     */
    public                  PhoneUser       user;
    public  static          Context         context;
    private static          DaoSession      daoSession;
    private static final    String          APP_ID      =       "7d74b6a586";
    private static final    String          TAG         =       "MyApp";
    public  static          boolean         isStart     =       false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 配置保存初始化
        SharedConfigUtil.init(context);
        // greenDao 初始化
        initGrennDao();
        // 腾讯 Bugly 初始化
        initBugle();
        // 日志logger初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .tag("syt")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        // 主题初始化
        initTheme();
    }

    /**
     * 判定是否开启黑夜模式
     * @return 是否开启
     */
    public static boolean getDarkModeStatus() {
        int mode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return mode == Configuration.UI_MODE_NIGHT_YES;
    }

    /**
     * 腾讯 Bugly 初始化
     */
    private void initBugle() {
        deleteApk();

        /** Beta高级设置  这是腾讯Bugly的补充设置
         * true表示app启动自动初始化升级模块;
         * false不会自动初始化;
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false，
         * 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级;
         * false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 10 * 1000;

        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 10 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源;
         */
//        Beta.largeIconId = R.drawable.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
         */
//        Beta.smallIconId = R.drawable.ic_launcher;

        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
//        Beta.defaultBannerId = R.drawable.ic_launcher;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 在 api > 29 以后 这个就会失效
         * 换成 Android/data/${applicationId}/files/apk/ 路径
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
//        Beta.storageDir = Environment.getExternalStorageState(new File(Environment.DIRECTORY_DOWNLOADS));


        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(SytMainActivity.class);

        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
//        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;

        /* 设置更新状态回调接口 */
        Beta.upgradeStateListener = new UpgradeStateListener() {
            @Override
            public void onUpgradeFailed(boolean b) {
                Log.d(TAG, "失败onUpgradeFailed: "  + b);
            }

            @Override
            public void onUpgradeSuccess(boolean b) {
                Log.d(TAG, "成功onUpgradeSuccess: " + b);
            }

            @Override
            public void onUpgradeNoVersion(boolean b) {
                Log.d(TAG, "没有新版本onUpgradeNoVersion: " + b);
            }

            @Override
            public void onUpgrading(boolean b) {
                Log.d(TAG, "onUpgrading: " + b);
            }

            @Override
            public void onDownloadCompleted(boolean b) {
                Log.d(TAG, "onDownloadCompleted: " + b);
            }
        };

        /**
         * 腾讯 Bugly 初始化
         * 2参数 appId
         * 3参数 debug 开关 建议在测试阶段建议设置成true，发布时设置为false。
         */
        Bugly.init(getApplicationContext(), APP_ID, true);
    }

    /**
     * 删除下载路径下面的apk文件
     */
    private void deleteApk() {
        //  /storage/emulated/0/Download/${applicationId}/.beta/apk
        File path = new File("/storage/emulated/0/Download/" + getApplicationInfo().packageName + "/.beta/apk");
        String[] list = path.list();
        if (list != null) {
            for (String name : list) {
                new File(path + "/" + name).delete();
            }
        }
        // Android/data/${applicationId}/files/apk/ 也清空
        File path2 = new File("/storage/emulated/0/Android/data/" + getApplicationInfo().packageName + "/files/apk/");
        list = path2.list();
        if (list != null) {
            for (String name : list) {
                new File(path2 + "/" + name).delete();
            }
        }
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

    /**
     * 主题初始化
     */
    private void initTheme() {
        // 记录系统的主题设置  改成原生的深色模式
        if (SharedConfigUtil.getNightOnOff() == -1) {
            if (getDarkModeStatus()) {
                SharedConfigUtil.saveNightOnOff(2);
            } else {
                SharedConfigUtil.saveNightOnOff(1);
            }
        }
        // 没有设置就执行系统主题
        if (SharedConfigUtil.getNightOnOff() == -1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else if (SharedConfigUtil.getNightOnOff() == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (SharedConfigUtil.getNightOnOff() == 2) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }
}
