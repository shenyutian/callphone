package com.syt.cellphone.base;

import com.syt.cellphone.BuildConfig;
import com.syt.cellphone.util.SharedConfigUtil;

/**
 * @author：syt
 * Date: 2019-12-05
 * 作用: 配置类
 */
public class Config {

    /**
     * toastSwitch 底部通知开关
     * log开关 1-5 2为debug
     * url 服务器路径
     * 底部菜单选项 bottomMenu
     */
    public  static boolean toastSwitch = BuildConfig.DEBUG;
    public  static int logLevel = 2;
//    public  static String url = "http://111.229.170.213:8001/";
    public  static String url = "http://47.115.43.73:8001/";
//    public  static String url = "http://10.10.10.50:8001/";
//    public  static String url = "http://10.0.2.2:8001/";

    /**
     * 虹软人脸 key + id
     */
    public static final String APP_ID = "8wg3VuJ8j5Fw2pNqg9gfrjAh5Ggar7ZahDAUewH7KmbY";
    public static final String SDK_KEY = "CbXWbZT9e8Nx2KmGN2ivPjSHtGoJr6rzevotMjKEorBg";


    private static int  bottomMenu = SharedConfigUtil.getBottomMenus();

    /**
     * 获取底部菜单
     * @return 底部菜单
     */
    public static int getBottomMenu() {
        return bottomMenu;
    }

    /**
     * 设置底部菜单
     * @param bottomMenus 底部菜单
     */
    public static void setBottomMenu(int bottomMenus) {
        bottomMenu = bottomMenus;
    }

}
