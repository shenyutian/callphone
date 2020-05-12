package com.syt.cellphone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @author shenyutian
 * @data 2020/5/12 1:57 PM
 * 功能 自定义滑动行为
 */
public class MyFabBehavior extends CoordinatorLayout.Behavior<View> {

    public MyFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 确定所提供的子视图是否有另一个特定的同级视图作为布局从属。
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        // 使得当前view 从属 AppBarLayout
        return dependency instanceof AppBarLayout;
    }

    /**
     *  响应顶部布局的变化
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        // 顶部布局改变数值
        float translationY = Math.abs(dependency.getTop());
        child.setTranslationY(translationY);

        return true;
    }
}
