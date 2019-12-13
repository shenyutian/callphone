package com.syt.cellphone.ui;

import com.syt.cellphone.base.BaseView;

/**
 * author：syt
 * Date: 2019-12-05
 * 作用: 主显示视图
 */
interface SytMainView extends BaseView {

    /**
     * 显示碎片
     * @param menuNum 第一个碎片 1-4
     */
    void showFragment(int menuNum);

}
