package com.syt.cellphone.ui.phone.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.syt.cellphone.R;
import com.syt.cellphone.adapter.PhoneBaseAdapter;
import com.syt.cellphone.adapter.SearchAdapter;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.ui.phone.details.PhoneDetailsActivity;
import com.syt.cellphone.util.ToastUtil;

import java.util.Objects;

import butterknife.BindView;

/**
 * @author：syt
 * 查询活动，也用来实现分类了
 */
public class SearchActivity extends BaseActivity<SearchPresener> implements SearchView {


    /**
     * --------------------控件介绍--------------------
     * barSearch            标题布局
     * rvSearchRecommend    热门推荐
     * rvSearchHistory      搜索历史
     * rvSearchResponse     搜索结果
     * etSearchInput        搜索输入框
     * ivSearchIc           搜索按钮图标
     * clSearchNoData       搜索界面提示布局
     */
    @BindView(R.id.bar_search)
    ConstraintLayout barSearch;
    @BindView(R.id.rv_search_recommend)
    RecyclerView rvSearchRecommend;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    @BindView(R.id.rv_search_response)
    RecyclerView rvSearchResponse;
    @BindView(R.id.et_search_input)
    EditText etSearchInput;
    @BindView(R.id.cl_search_no_data)
    ConstraintLayout clSearchNoData;
//    @BindView(R.id.iv_search_ic)
//    ImageView ivSearchIc;

    /**
     * --------------------参数结束-----------------------
     * searchResultAdapter      搜索结果适配器
     * searchRecommendAdapter   搜索推荐适配器
     * searchLocalAdapter       搜索历史适配器
     */
    private PhoneBaseAdapter searchResultAdapter;
    private SearchAdapter searchLocalAdapter;
    private SearchAdapter searchRecommendAdapter;

    @Override
    protected SearchPresener createPresenter() {
        return new SearchPresener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        String content = getIntent().getStringExtra("content");
        // 判定是否有传来的分类结果
        if (content != null && !content.isEmpty()) {
            // 隐藏搜索提示布局，显示搜索结果
            rvSearchResponse.setVisibility(View.VISIBLE);
            clSearchNoData.setVisibility(View.GONE);
            etSearchInput.setText(content);
            presenter.handleSearchResult(getIntent().getStringExtra("content"));
        }
        initResultRv();
        // 加载历史记录
        initSearchLocalRv();
        // 搜索推荐
        initSearchRecommendRv();
    }

    private void initSearchRecommendRv() {
        // 设置横向2行，瀑布流。
        rvSearchRecommend.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        searchRecommendAdapter = new SearchAdapter(R.layout.item_search_text, presenter.getSearchHistoryList());
        // 点击事件
        searchRecommendAdapter.setOnItemChildClickListener(
                (adapter, view, position) -> {
                    etSearchInput.setText(presenter.getSearchHistoryList().get(position).getSearchContent());
                    // 加载搜索
                    presenter.handleSearchResult(etSearchInput.getText().toString());
                });
    }

    private void initSearchLocalRv() {
        // 设置横向2行，瀑布流。
        rvSearchHistory.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        searchLocalAdapter = new SearchAdapter(R.layout.item_search_text, presenter.getSearchHistoryList());
        // 点击事件
        searchLocalAdapter.setOnItemChildClickListener(
            (adapter, view, position) -> {
                etSearchInput.setText(presenter.getSearchHistoryList().get(position).getSearchContent());
                // 加载搜索
                presenter.handleSearchResult(etSearchInput.getText().toString());
        });
    }

    /**
     * 搜索结果配置
     */
    private void initResultRv() {
        // 搜索适配器
        rvSearchResponse.setLayoutManager(new LinearLayoutManager(getContext()));
        searchResultAdapter = new PhoneBaseAdapter(R.layout.item_phone, presenter.getSearchResult());
        // 底部的加载事件
        searchResultAdapter.getLoadMoreModule().setOnLoadMoreListener(
            () -> {
                if (etSearchInput.getText() == null
                        || etSearchInput.getText().toString().isEmpty()) {
                    // 显示暂无搜索
                    searchResultAdapter.addHeaderView(new TextView(context));
                } else {
                    // 加载搜索
                    presenter.handleSearchResult(etSearchInput.getText().toString());
                }
            }
        );
//        presenter.loadingSearchResult(etSearchInput.getText().toString())

        // 点击事件
        searchResultAdapter.setOnItemClickListener(
                (adapter, view, position) -> {
                    ToastUtil.makeText(Integer.toString(position));
                    // 进行跳转
                    Intent startPhoneDetails = new Intent(context, PhoneDetailsActivity.class);
                    // 设备id
                    startPhoneDetails.putExtra("phoneId", presenter.getSearchResult().get(position).getBaseId());
                    context.startActivity(startPhoneDetails);
                }
        );
        // 执行适配器
        rvSearchResponse.setAdapter(searchResultAdapter);
    }

    @Override
    public void resetSearchRv() {
        // 隐藏搜索提示布局，显示搜索结果
        rvSearchResponse.setVisibility(View.VISIBLE);
        clSearchNoData.setVisibility(View.GONE);
        //刷新界面
        searchResultAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        Objects.requireNonNull(searchResultAdapter.getLoadMoreModule()).loadMoreComplete();
    }

    @Override
    public void resettoBottom() {
        if (searchResultAdapter.getFooterLayoutCount() > 0) {
            return;
        }
        TextView tvBottom = new TextView(context);
        tvBottom.setText("我也是有底线的");
        //刷新界面
        searchResultAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        searchResultAdapter.getLoadMoreModule().loadMoreComplete();
        //关闭刷新
        searchResultAdapter.getLoadMoreModule().loadMoreEnd();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }


}
