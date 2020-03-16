package com.syt.cellphone.ui.user;

import com.syt.cellphone.base.BaseView;
import com.syt.cellphone.pojo.Registered;


/**
 * @author shenyutian
 * @data 2020/3/12 4:09 PM
 * 功能 注册接口
 */
public interface RegisteredView extends BaseView {

    /**
     * 注册成功后，跳出过渡动画，然后关闭注册 activity 跳转到首页
     * @param user 注册成功的用户信息
     */
    void registeredSuccess(Registered user);

    /**
     * 注册失败后，跳出失败动画。
     * @param error 错误信息
     */
    void registeredError(Registered error);

    /**
     * 发送邮件失败
     * @param msg 异常信息
     */
    void emailError(String msg);

    /**
     * 发送邮件成功
     */
    void emailSuccess();
}
