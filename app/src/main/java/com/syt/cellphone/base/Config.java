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

    /**
     * https 正式的时候上线，暂时不上。
     */
    public  static String url =
        BuildConfig.DEBUG ?
                "http://47.115.43.73:8001/" : "https://shenyutian.xyz";

    /**
     * 虹软人脸 key + id
     */
    public static final String APP_ID = "8wg3VuJ8j5Fw2pNqg9gfrjAh5Ggar7ZahDAUewH7KmbY";
    public static final String SDK_KEY = "CbXWbZT9e8Nx2KmGN2ivPjSHtGoJr6rzevotMjKEorBg";

    /**
     * RSA 加密算法 公钥
     */
    public static final String KEY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4spsGjnq/6NV0/G664Ij0Q2O+HQOkRM/I4/9QGttLJMH1R05C5jlsz/J74aKN5EL9HhIEKfwr8FwTvQn54IZgsg+bEfw9WMdm6MeJMc6JSI4MviTaSU+3V90i0gBWxJa8irG8rl0aKaip3nDB/ugBzmM38GsUTpTHkkD6SO19uQIDAQAB";


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
