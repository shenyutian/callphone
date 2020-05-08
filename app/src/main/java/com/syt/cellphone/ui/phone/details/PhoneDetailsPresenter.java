package com.syt.cellphone.ui.phone.details;

import android.content.Intent;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.Estimate;
import com.syt.cellphone.pojo.PhoneDetails;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.ui.SytMainActivity;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.SharedConfigUtil;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author shenyutian
 * @data 2020-01-19 17:37
 * 功能
 */
public class PhoneDetailsPresenter extends BasePresenter<PhoneDetailsView> {

    /**
     *   data           基础数据集合
     *   phoneId        请求phoneId
     *   newEstimate    新的评价
     *
     */
    private PhoneDetails data;
    private int phoneId;
    private Estimate newEstimate;

    PhoneDetailsPresenter(PhoneDetailsView baseView) {
        super(baseView);
    }

    public PhoneDetails getData() {
        return data;
    }

    public Estimate getNewEstimate() {
        return newEstimate;
    }

    public void handlePhoneDetails(int phoneId) {
        if (phoneId == 0) {
            LogUtil.e("暂无设备");
            // 提示暂无设备

        } else {
            this.phoneId = phoneId;
            addDisposable(apiServer.getPhoneDetailsById(phoneId),
                    new BaseObserver<PhoneDetails>(baseView) {
                        @Override
                        public void onSuccess(PhoneDetails o) {
                            data = o;
                            baseView.resetPhoneDetails();
                        }

                        @Override
                        public void onError(String msg) {

                        }
                    }, 0);
        }
    }

    public void handUnloadEstimate(Estimate estimate) {
        if (estimate == null) {
            Logger.e("评价不能为空");
            return;
        }
        // 还需要一个请求头，来发送token。
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(estimate));

        addDisposable(apiServer.setEstimate(body), new BaseObserver<JSONObject>(baseView) {

            @Override
            public void onSuccess(JSONObject o) {
                handleEstimate(o);
            }

            @Override
            public void onError(String msg) {
                LogUtil.d("onError: " + msg);
//                LogUtil.d("Thread : " + Thread.currentThread().getName());
//                handleEstimate(msg);
                // 请登录
                Toast.makeText(getBaseView().getContext().getApplicationContext(), "请先登录", Toast.LENGTH_LONG).show();
                //重启activity, 设置第4个fragment
                Config.setBottomMenu(4);
                Intent intent = new Intent(context, SytMainActivity.class);
                intent.putExtra("param", 4);
                // 去除context 跳转限制
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }, 0);
    }

    private void handleEstimate(JSONObject msg) {

        LogUtil.d("handleEstimate" + msg.toString());

        if (msg.get("msg").equals("0")) {

            newEstimate = msg.getObject("estimate", Estimate.class);

            baseView.refresh();

        } else {
            Logger.e("添加评价失败 " + msg.toJSONString());
            Toast.makeText(getBaseView().getContext().getApplicationContext(), msg.get("msg").toString(), Toast.LENGTH_LONG).show();
            //重启activity, 设置第4个fragment
//            Config.setBottomMenu(4);
//            Intent intent = new Intent(context, SytMainActivity.class);
//            intent.putExtra("param", 4);
//            getBaseView().getContext().getApplicationContext().startActivity(intent);
        }
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
                    baseView.closeLoginDialog();
                }
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }, 0);
    }
}
