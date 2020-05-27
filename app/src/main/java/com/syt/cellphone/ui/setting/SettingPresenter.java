package com.syt.cellphone.ui.setting;

import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.pojo.UploadFiles;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.util.UploadFilesUtils;

import java.io.File;

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

        File[] files = new File[1];
        files[0] = imgFile;
        UploadFilesUtils.uploadFiles(files, new UploadFilesUtils.UploadCallback() {
            @Override
            public void success(UploadFiles uploadFiles) {
                if (uploadFiles.getCode() == 0) {
                    // 头像路径
                    String portraitSrc = uploadFiles.getData().get(0);
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
            public void error(String msg) {
                Logger.d("上传头像失败！");
                Toast.makeText(context, "上传头像失败", Toast.LENGTH_SHORT).show();
            }
        });

//        RequestBody requestBody = RequestBody.create(imgFile, MediaType.parse("multipart/form-data"));
//        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imgFile.getName(), requestBody);
//        addDisposable(apiServer.upload(body), new BaseObserver<UploadFile>(baseView) {
//            @Override
//            public void onSuccess(UploadFile o) {
//                if (o.getCode() == 0) {
//                    // 头像路径
//                    String portraitSrc = o.getData().getSrc();
//                    Logger.d("上传头像成功" + portraitSrc);
//
//                    // 保存头像
//                    addDisposable(apiServer.setPortrait(portraitSrc), new BaseObserver<JSONObject>(baseView) {
//                        @Override
//                        public void onSuccess(JSONObject o) {
//                            Integer msg = o.getInteger("msg");
//                            if (msg == 1) {
//                                // 头像上传成功
//                                String portraitSrc1 = o.getString("portraitSrc");
//                                baseView.refreshPortrait(portraitSrc);
//                            }
//                        }
//
//                        @Override
//                        public void onError(String msg) {
//                            Logger.d("保存头像失败！");
//                        }
//                    }, 0);
//
//                } else {
//                    Logger.d("上传头像失败！");
//                }
//            }
//
//            @Override
//            public void onError(String msg) {
//                Logger.d("上传头像失败！");
//            }
//        }, 0);
    }
}
