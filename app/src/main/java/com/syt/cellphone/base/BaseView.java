package com.syt.cellphone.base;

import android.content.Context;

/**
 * author：syt
 * Date: 2019-11-22
 * 作用: 基础视图
 */
public interface BaseView {

    /**
     *
     * @return 当前的context
     */
    Context getContext();

    /**
     * 显示提示
     */
    void showMsg(String msg);

    /**
     * @param model 错误码
     */
    void onErrorCode(BaseBean model);

    /**
     * 隐藏dialog
     */
    void hideLoading();
}
