package com.syt.cellphone.ui.setting.feedback;

import android.content.Context;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;

/**
 * 反馈功能视图
 * @author syt
 * @data 2020.5.8
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackView {

    @Override
    protected FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
