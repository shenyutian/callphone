package com.syt.cellphone.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：syt
 * Date: 2019-12-05
 * 作用: 基础碎片
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    public Context context;
    protected P fpresenter;
    Unbinder unbinder;


    /**
     * 初始化 p
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化、绑定数据
     */
    protected abstract void initData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(initLayout(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        fpresenter = initPresenter();
        initData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (fpresenter != null) {
            fpresenter.detachView();
        }
        unbinder.unbind();
    }
}
