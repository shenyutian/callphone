package com.syt.cellphone.util;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

/**
 * @author shenyutian
 * @data 2020/5/19 10:27 AM
 * 功能 移动视图工具类
 */
public class AnimatorScrollUtil {


    private static final String TAG = "AnimatorScrollUtil";

    public static void scrollTo(View view, int x, int y) {

        if (view == null) {
            Log.e(TAG, "滑动视图不能为空");
            return;
        }

        ValueAnimator animator = ValueAnimator.ofInt(x, y);
        animator.setDuration(750);
        animator.addUpdateListener(animation -> {
            view.scrollTo(x, y);
        });

        animator.start();
    }

}
