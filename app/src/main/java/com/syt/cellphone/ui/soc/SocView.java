package com.syt.cellphone.ui.soc;

import com.syt.cellphone.base.BaseView;

/**
 * @author：syt Date: 2019-12-12
 * 作用: Soc视图
 */
public interface SocView extends BaseView {

    /**
     * 刷新视图列表
     */
    void refreshRv();

    /**
     * 显示没有数据 = 我也是有底线的。
     */
    void showNoData();
}
