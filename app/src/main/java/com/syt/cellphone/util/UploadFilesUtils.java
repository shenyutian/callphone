package com.syt.cellphone.util;

import com.syt.cellphone.net.ApiServer;
import com.syt.cellphone.pojo.UploadFiles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author shenyutian
 * @data 2020/5/14 5:35 PM
 * 功能 上传文件工具类
 */
public class UploadFilesUtils {

    /**
     * 文件上传路径
     */
    private static final String UPLOAD_URL = "http://47.115.43.73:8111/";

    /**
     * 上传文件工具类
     * @param files 文件集
     * @return 结果
     */
    public static UploadFiles uploadFiles(File[] files) {

        RequestBody body = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                // 支持失败重新
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UPLOAD_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);

        apiServer.uploadFiles(body);

    }

}
