package com.syt.cellphone.util;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    // 服务器映射的 url
    private static String url = "http://9jdtt6.natappfree.cc";

    /**
     * post 请求工具类
     */
    public static String postUrl(String body, Map data) {
        String responseBody = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        // 请求内容
        RequestBody requestBody = FormBody
                .create(MediaType.parse("application/json; charset=utf-8"), json);
        //  设置请求参数内容
        Request request = new Request.Builder()
                .url(url + body)
                .post(requestBody)
                .build();
        // 设置响应
        Response response = null;
        // 3 执行请求操作
        try {
            response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果, 请求成功则解析
                responseBody = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    /**
     * get 请求工具类
     */
    public static String getUrl(String body) {
        String responseBody = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        //  设置请求参数内容
        Request request = new Request.Builder()
                .url(url + body)
                .build();
        // 设置响应
        Response response = null;
        // 3 执行请求操作
        try {
            response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果, 请求成功则解析
                responseBody = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

}
