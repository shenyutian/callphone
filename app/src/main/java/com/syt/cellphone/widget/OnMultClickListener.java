package com.syt.cellphone.widget;

import android.view.View;

/**
 * @author shenyutian
 * @data 2020/3/6 5:10 PM
 * 功能 点击间隔 抽象类
 */
public abstract class OnMultClickListener implements View.OnClickListener {

    /**
     *     设置2次点击按钮之间的点击间隔不能少于1000毫秒
      */
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long      lastClickTime;

    public abstract void onMultClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            onMultClick(v);
        }
    }
}
