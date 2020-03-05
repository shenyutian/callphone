package com.syt.cellphone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author shenyutian
 * @data 2020/3/5 11:58 AM
 * 功能 无法点击的EditText，防止和父控件冲突
 */
public class EditTextClickOn extends androidx.appcompat.widget.AppCompatEditText {

    public EditTextClickOn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *     关闭控件的点击事件分发
      */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//      表示不能对该事件进行处理。进而让父控件自己处理事件。
        return false;
    }
}
