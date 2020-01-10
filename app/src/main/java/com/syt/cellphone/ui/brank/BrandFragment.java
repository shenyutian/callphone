package com.syt.cellphone.ui.brank;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.util.ToastUtil;

import butterknife.BindView;

/**
 * @author shenyutian
 * @data 2020-01-08 18:34
 * 功能 品牌fragment
 */
public class BrandFragment extends BaseFragment<BrandPresenter> implements BrandView {

    @BindView(R.id.rv_brand_list)
    RecyclerView rvBrandList;
    BrandAdapter brandAdapter;

    @Override
    protected BrandPresenter initPresenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initData() {
        // 设置数据列表
        initRv();
        // 请求品牌数据
        fpresenter.refreshData();
    }

    @Override
    public void showBrandData() {
        // 试试刷新有没有用
        brandAdapter.notifyDataSetChanged();
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

    private void initRv() {
        rvBrandList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // 设置适配器
        brandAdapter = new BrandAdapter(R.layout.item_brand, fpresenter.getTrademarkList());
        // 设置点击事件
        rvBrandList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.makeText(fpresenter.getTrademarkList().get(position).getTrademarkName());
            }
        });
        // 设置适配器
        rvBrandList.setAdapter(brandAdapter);
    }
}
