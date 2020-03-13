package com.syt.cellphone.ui.user;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneUser;

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
        addDisposable(apiServer.setUserEmail(email), new BaseObserver<String>(baseView) {

            @Override
            public void onSuccess(String o) {
                if ("1".equals(o)) {
                    // 邮件发送成功
                } else {
                    //
                }
            }

            @Override
            public void onError(String msg) {

            }
        }, 0);
    }

    /**
     * 处理注册信息
     * @Param user 注册信息
     */
    public void handRegister(PhoneUser user) {

        // 注册成功失败
        if (false) {
            baseView.registeredSuccess(user);
        } else {
            baseView.registeredError();
        }
    }
}
