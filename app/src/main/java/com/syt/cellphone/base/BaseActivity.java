package com.syt.cellphone.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.syt.cellphone.util.LogUtil;
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
        ToastUtil.makeText(msg);
    }

    @Override
    public void onErrorCode(BaseBean model) {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 启动
     * 消息传递
     * @param object 目标对象
     * @param data 内容1
     */
    public void actionStart(Class<?> object, String data) {
        Intent intent = new Intent(context, object);
        intent.putExtra("param", data);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d("intent错误");
        }
    }
}
