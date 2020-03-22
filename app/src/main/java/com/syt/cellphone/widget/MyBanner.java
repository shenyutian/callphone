package com.syt.cellphone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.syt.cellphone.R;
import com.youth.banner.Banner;

/**
 * @author shenyutian
 * @data 2020/3/5 3:28 PM
 * 功能 重写控件，通知父控件 = 我能滑动
 */
public class MyBanner extends Banner {

    public MyBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.recyclerViewStyle);
    }

    public MyBanner(Context context) {
        this(context, null);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //解决recyclerView和viewPager的滑动影响
        //当滑动recyclerView时，告知父控件不要拦截事件，交给子view处理
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
