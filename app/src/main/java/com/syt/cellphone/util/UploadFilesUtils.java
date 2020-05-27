package com.syt.cellphone.util;

import com.orhanobut.logger.Logger;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.net.ApiServer;
import com.syt.cellphone.pojo.UploadFiles;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
     * 上传多个文件
     * @param files 文件集
     * @return 结果
     */
    public static void uploadFiles(File[] files, UploadCallback callback) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            RequestBody body = RequestBody.create(file, MediaType.parse("image/png"));
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
                .baseUrl(Config.fileUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);

        Observable<UploadFiles> filesObservable = apiServer.uploadFiles(body);

        filesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadFiles>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadFiles uploadFiles) {
                        Logger.d("上传文件成功");
                        callback.success(uploadFiles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("上传文件失败\t" + e.getMessage());
                        callback.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface UploadCallback {

        void success(UploadFiles uploadFiles);

        void error(String msg);
    }

}
