package com.syt.cellphone.util;

import android.content.Context;

/**
 * Created by hzsp-rz on 2016/8/seventeen.
 */

public class DpPx {
    // dp转换像素
    public static int Dp2Px(Context context, float dp)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
