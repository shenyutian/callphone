package com.syt.cellphone.ui.user;

import com.alibaba.fastjson.JSONObject;
import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.pojo.Registered;
import com.syt.cellphone.util.DialogUtils;

/**
 * @author shenyutian
 * @data 2020/3/12 4:11 PM
 * 功能 注册数据处理
 */
public class RegisterPresenter extends BasePresenter<RegisteredView> {

    public RegisterPresenter(RegisteredView baseView) {
        super(baseView);
    }

    /**
     * 网络请求验证码
     * @param email 注册的邮箱地址
     */
    public void handleVerificationCode(String email) {
        addDisposable(apiServer.setUserEmail(email), new BaseObserver<JSONObject>(baseView) {
            @Override
            public void onSuccess(JSONObject o) {
                if (o.getString("message").equals("1")) {
                    // 邮件发送成功
                    baseView.emailSuccess();
                } else {
                    // 邮箱有问题
                    baseView.emailError(o.getString("message"));
               }
            }

            @Override
            public void onError(String msg) {
                // 邮箱有问题
                baseView.emailError(msg);
            }
        }, 0);
    }

    /**
     * 处理注册信息
     * @Param user 注册信息
     */
    public void handRegister(PhoneUser user) {

        DialogUtils.showLoadingDialog(context, "注册中");
        addDisposable(apiServer.setRegistered(user), new BaseObserver<Registered>(baseView) {
            @Override
            public void onSuccess(Registered o) {
                // 注册成功
                if (o.getMsg() < 1) {
                    baseView.registeredSuccess(o);
                } else {
                    baseView.registeredError(o);
                }
                DialogUtils.dismissLoadingDialog();
            }

            @Override
            public void onError(String msg) {
                baseView.emailError(msg);
                DialogUtils.dismissLoadingDialog();
            }
        }, 0);
    }
}
