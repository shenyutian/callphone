package com.syt.cellphone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;

public class SytMainActivity extends BaseActivity<SytMainPresenter> implements SytMainView {

    @Override
    protected SytMainPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_syt_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syt_main);
    }

    @Override
    public Context getContext() {
        return null;
    }
}
