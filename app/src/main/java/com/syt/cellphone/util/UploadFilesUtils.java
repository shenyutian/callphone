package com.syt.cellphone.util;

import com.syt.cellphone.net.ApiServer;
import com.syt.cellphone.pojo.UploadFiles;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("files", file.getName(), body);
        }
        RequestBody body = builder.build();


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

//        apiServer.uploadFiles(body).subscribeOn(Schedulers.io())
//                .subscribe()new Observable<ResponseBody>() {
//
//            @Override
//            protected void subscribeActual(Observer<? super ResponseBody> observer) {
//
//            }
//        });

        return null;
    }

}
