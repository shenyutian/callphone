package com.syt.cellphone.net;

import com.syt.cellphone.BuildConfig;

/**
 * author：syt
 * Date: 2019-11-22
 * 作用: 网络url
 */
public class HttpUrls {

    public static String BaseUrl = BuildConfig.DEBUG ?
            "http://weixt.spacetech.com.cn:8090/weixt/api/" : " http://www.weixiaotong.com.cn/weixt/api/";

    public static String faceBaseUrl = "http://static.weixiaotong.com.cn/";

    public static final String APP_ID = "9KW6LhpQbfpzKMnVQq6NP12UW8gchrqjqdwaDLxsgMgs";
    public static final String SDK_KEY = "DFv1RS5qqtizAspBykw8f3tAhHN1FmWKMwEH56WrT3RR";


}
