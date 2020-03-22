package com.syt.cellphone.ui.setting;

import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.pojo.UnloadFile;
import com.syt.cellphone.util.SharedConfigUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author：syt Date: 2019-12-09
 * 作用: 设置
 */
public class SettingPresenter extends BasePresenter<SettingView> {


    public SettingPresenter(SettingView baseView) {
        super(baseView);
    }

    /**
     * 处理登录逻辑
     * @param user 用户登录数据
     */
    public void handleUserLogin(PhoneUser user) {
        addDisposable(apiServer.setUserLogin(user), new BaseObserver<PhoneUser>(baseView) {
            @Override
            public void onSuccess(PhoneUser o) {
                Toast.makeText(getBaseView().getContext().getApplicationContext(), o.getMessage(), Toast.LENGTH_SHORT).show();
                if ("登录成功".equals(o.getMessage())) {
                    SharedConfigUtil.saveToken(o.getToken());
                    baseView.refresh();
                }
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }, 0);
    }

    public void uploadProtrait(File imgFile) {
        RequestBody requestBody = RequestBody.create(imgFile, MediaType.parse("multipart/form-data"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imgFile.getName(), requestBody);
        addDisposable(apiServer.unload(body), new BaseObserver<UnloadFile>(baseView) {
            @Override
            public void onSuccess(UnloadFile o) {
                if (o.getCode() == 0) {
                    // 头像路径
                    String portraitSrc = o.getData().getSrc();
                    Logger.d("上传头像成功" + portraitSrc);

                    // 保存头像
                    addDisposable(apiServer.setPortrait(portraitSrc), new BaseObserver<JSONObject>(baseView) {
                        @Override
                        public void onSuccess(JSONObject o) {
                            Integer msg = o.getInteger("msg");
                            if (msg == 1) {
                                // 头像上传成功
                                String portraitSrc1 = o.getString("portraitSrc");
                                baseView.refreshPortrait(portraitSrc);
                            }
                        }

                        @Override
                        public void onError(String msg) {
                            Logger.d("保存头像失败！");
                        }
                    }, 0);

                } else {
                    Logger.d("上传头像失败！");
                }
            }

            @Override
            public void onError(String msg) {
                Logger.d("上传头像失败！");
            }
        }, 0);
    }
}
