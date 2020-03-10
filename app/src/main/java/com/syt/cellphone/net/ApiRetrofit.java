package com.syt.cellphone.net;

import android.util.Log;
import android.webkit.WebSettings;

import com.syt.cellphone.base.Config;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.util.SharedConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author：syt
 * Date: 2019-11-22
 * 作用: 网络请求构建器
 */
public class ApiRetrofit {

    public final String BASE_SERVER_URL = Config.url;

    private Retrofit retrofit;
    private OkHttpClient client;
    private ApiServer apiServer;

    private String TAG = "ApiRetrofit";

    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = chain -> {
        //请求体
        Request request = chain.request();
        //启动时间
        long startTime = System.currentTimeMillis();
        //返回体
        Response response = chain.proceed(chain.request());
        //结束时间
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MediaType mediaType = Objects.requireNonNull(response.body()).contentType();
        String content = Objects.requireNonNull(response.body()).string();

//        Buffer buffer = new Buffer();
//        request.body().writeTo(buffer);
//        Charset charset = Objects.requireNonNull(request.body().contentType().charset(UTF_8));
//        String params = buffer.readString(charset);
//        String decodeStr = URLDecoder.decode(params, "UTF-8");

        String url = request.url().toString();

        Log.e(TAG, "----------  Request Start   ----------------");
        Log.e(TAG, "| " + request.toString() + request.headers().toString());
        Log.e(TAG, "| Response: " + content);
//        Log.e(TAG, "| request:  " + decodeStr);
        Log.e(TAG, "----------    Request End:" + duration + "毫秒----------");

        //发送响应结果
        return response.newBuilder()
                .body(ResponseBody.create(content, mediaType))
                .build();
    };


    private ApiRetrofit() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("User-Agent")
                                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(MyApp.context))
                                .addHeader("token", SharedConfigUtil.getToken())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        //debug模式加拦截器
        //if (BuildConfig.DEBUG) {
        okhttpBuilder.addInterceptor(interceptor);
        //}
        client = okhttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    //单例创建
    public static ApiRetrofit getInstance() {
        return SingetonHolder.apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }

    private static class SingetonHolder {
        private static ApiRetrofit apiRetrofit = new ApiRetrofit();
    }
}

