package com.syt.cellphone.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

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
     * @return 当前演讲者 p
     */
    protected abstract int getLayoutId();
    /**
     * 当前演讲者 p
     */
    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
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
        ToastUtil.makeText(context, msg);
    }

    @Override
    public void onErrorCode(BaseBean model) {

    }

    @Override
    public void hideLoading() {

    }

}
