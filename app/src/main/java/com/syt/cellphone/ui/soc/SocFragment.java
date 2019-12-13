package com.syt.cellphone.ui.soc;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;

import butterknife.BindView;

/**
 * soc 处理器 列表
 */
public class SocFragment extends BaseFragment<SocPresenter> implements SocView {


    @BindView(R.id.rv_soc_list)
    RecyclerView rvSocList;
    SocAdapter socAdapter;

    @Override
    protected SocPresenter initPresenter() {
        return new SocPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_soc;
    }

    @Override
    protected void initData() {
        fpresenter.getNetSocList(1);
        // Rv处理
        initRv();
    }

    private void initRv() {
        // 设置布局方式
        rvSocList.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        socAdapter = new SocAdapter(fpresenter.getSocList(), getContext());
        // 执行适配器
        rvSocList.setAdapter(socAdapter);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void onErrorCode(BaseBean model) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void refreshRv() {
        socAdapter.notifyDataSetChanged();
    }
}
