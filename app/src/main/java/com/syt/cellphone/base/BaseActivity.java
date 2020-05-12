package com.syt.cellphone.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.syt.cellphone.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：syt
 * Date: 2019-12-05
 * 作用: 基础Activity
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public Context context;
    public P presenter;
    private Unbinder unbinder;

    /**
     * @return 当前演讲者 p
     */
    protected abstract P createPresenter();
    /**
     * @return 获取当前的布局id
     */
    protected abstract int getLayoutId();
    /**
     * 导入布局数据
     */
    protected abstract void initData();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        // 关闭屏幕旋转 锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        unbinder = ButterKnife.bind(this);
        context = this;
        presenter = createPresenter();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        unbinder.unbind();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.makeText(msg);
    }

    @Override
    public void onErrorCode(BaseBean model) {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 关闭屏幕旋转
     */

}
