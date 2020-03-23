package com.syt.cellphone.ui.phone.details;

import com.syt.cellphone.base.BaseView;

/**
 * @author shenyutian
 * @data 2020-01-19 13:58
 * 功能
 */
public interface PhoneDetailsView extends BaseView {

    /**
     * 载入全部视图
     */
    void resetPhoneDetails();

    /**
     * 添加界面
     */
    void refresh();

    /**
     * 登录完成关闭登录窗口
     */
    void closeLoginDialog();
}
