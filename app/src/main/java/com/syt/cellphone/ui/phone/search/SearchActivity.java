package com.syt.cellphone.ui.phone.search;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.syt.cellphone.R;
import com.syt.cellphone.adapter.PhoneBaseAdapter;
import com.syt.cellphone.adapter.SearchAdapter;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.ui.phone.details.PhoneDetailsActivity;
import com.syt.cellphone.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author：syt 查询活动，也用来实现分类了
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
     * tvClassifyName       分类结果
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
    @BindView(R.id.tv_classify_name)
    TextView tvClassifyName;
    @BindView(R.id.tv_search_back)
    TextView tvSearchBack;
    @BindView(R.id.iv_search_ic)
    ImageView ivSearchIc;
    @BindView(R.id.tv_search_recommend)
    TextView tvSearchRecommend;
    @BindView(R.id.tv_search_history)
    TextView tvSearchHistory;
    @BindView(R.id.tv_search_history_clean)
    TextView getTvSearchHistoryClean;
    @BindView(R.id.refreshLayout_search_view)
    SmartRefreshLayout refreshLayoutSearchView;

    /**
     * --------------------参数结束-----------------------
     * searchResultAdapter      搜索结果适配器
     * searchRecommendAdapter   搜索推荐适配器
     * searchLocalAdapter       搜索历史适配器
     * pageindex                搜索的页码
     */
    private PhoneBaseAdapter searchResultAdapter;
    private SearchAdapter searchLocalAdapter;
    private SearchAdapter searchRecommendAdapter;
    private int     pageindex = 1;
    private String TAG = "SearchActivity";

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
            refreshLayoutSearchView.setVisibility(View.VISIBLE);
            clSearchNoData.setVisibility(View.GONE);
            ivSearchIc.setVisibility(View.GONE);
            tvClassifyName.setText(content);
            tvClassifyName.setVisibility(View.VISIBLE);
            etSearchInput.setVisibility(View.GONE);
            // 分类数据
            String content1 = getIntent().getStringExtra("content");
            etSearchInput.setText(content1);
            presenter.handleSearchResult(content1, pageindex);
        } else {
            // 弹出键盘
//            etSearchInput.requestFocus();
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

            // 监听键盘事件
            etSearchInput.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startSearch(etSearchInput.getText().toString().trim());
                }
                return false;
            });
            // 加载历史记录
            initSearchLocalRv();
            // 搜索推荐
            initSearchRecommendRv();
        }
        // 加载更新phone 列表视图
        initResultRv();
    }


    @OnClick({R.id.tv_search_back, R.id.iv_search_ic, R.id.tv_search_recommend, R.id.tv_search_history, R.id.tv_search_history_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_back:
                finish();
                break;
            case R.id.iv_search_ic:
                // 关闭输入法
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(etSearchInput, InputMethodManager.SHOW_IMPLICIT);
                startSearch(etSearchInput.getText().toString().trim());
                break;
            case R.id.tv_search_history_clean:
                // 清空历史
                presenter.handCleanHistory();
                break;
            default:
                break;
        }
    }

    private void initSearchRecommendRv() {
        rvSearchRecommend.setVisibility(View.VISIBLE);
        // 设置横向2行，瀑布流。
        rvSearchRecommend.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        searchRecommendAdapter = new SearchAdapter(R.layout.item_search_text, presenter.getSearchRecommendList());
        // 点击事件 搜索推荐
        searchRecommendAdapter.setOnItemClickListener(
                (adapter, view, position) -> {
                    etSearchInput.setText(presenter.getSearchRecommendList().get(position).getSearchContent());
                    // 加载搜索
                    startSearch(etSearchInput.getText().toString().trim());
                });
        rvSearchRecommend.setAdapter(searchRecommendAdapter);
    }

    private void initSearchLocalRv() {
        rvSearchHistory.setVisibility(View.VISIBLE);
        // 设置横向2行，瀑布流。
        rvSearchHistory.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        searchLocalAdapter = new SearchAdapter(R.layout.item_search_text, presenter.getSearchHistoryList());
        // 点击事件 搜索历史按钮
        searchLocalAdapter.setOnItemClickListener(
                (adapter, view, position) -> {
                    etSearchInput.setText(presenter.getSearchHistoryList().get(position).getSearchContent());
                    // 加载搜索
                    startSearch(etSearchInput.getText().toString().trim());
                });
        rvSearchHistory.setAdapter(searchLocalAdapter);
        searchLocalAdapter.notifyDataSetChanged();
    }

    /**
     * 搜索结果配置
     */
    private void initResultRv() {

        // 搜索适配器
        rvSearchResponse.setLayoutManager(new LinearLayoutManager(this));
        searchResultAdapter = new PhoneBaseAdapter(R.layout.item_phone, presenter.getSearchResult());
        // 下拉刷新加载事件
        refreshLayoutSearchView.setOnRefreshListener(refreshLayout -> {
            if (etSearchInput.getText() == null
                    || etSearchInput.getText().toString().isEmpty()) {
                // 显示暂无搜索
                searchResultAdapter.addHeaderView(new TextView(this));
                refreshLayout.finishRefresh(false);

            } else {
                // 底部加载搜索
                pageindex = 1;
                presenter.handleSearchResult(etSearchInput.getText().toString().trim(), pageindex);
                refreshLayout.finishRefresh(2000);
            }
        });
        // 上拉刷新事件
        refreshLayoutSearchView.setOnLoadMoreListener(refreshLayout -> {
            if (etSearchInput.getText() == null
                    || etSearchInput.getText().toString().isEmpty()) {
                // 显示暂无搜索
                searchResultAdapter.addHeaderView(new TextView(this));
                refreshLayout.finishRefresh(false);
            } else {
                // 底部加载搜索
                pageindex++;
                presenter.handleSearchResult(etSearchInput.getText().toString().trim(), pageindex);
                refreshLayout.finishRefresh(2000);
            }
            Log.e(TAG, "initResultRv: ");
        });
        // 点击事件
        searchResultAdapter.setOnItemClickListener(
                (adapter, view, position) -> {
                    ToastUtil.makeText(Integer.toString(position));
                    // 进行跳转
                    Intent startPhoneDetails = new Intent(this, PhoneDetailsActivity.class);
                    // 设备id
                    startPhoneDetails.putExtra("phoneId", presenter.getSearchResult().get(position).getBaseId());
                    context.startActivity(startPhoneDetails);
                }
        );
        // 执行适配器
        rvSearchResponse.setAdapter(searchResultAdapter);
    }

    /**
     * 刷新搜索结果
     */
    @Override
    public void resetSearchRv() {
        // 隐藏搜索提示布局，显示搜索结果
        rvSearchResponse.setVisibility(View.VISIBLE);
        clSearchNoData.setVisibility(View.GONE);
        // 刷新界面
        searchResultAdapter.notifyDataSetChanged();
        // 关闭的提醒刷新
        refreshLayoutSearchView.finishRefresh();
        refreshLayoutSearchView.finishLoadMore();
    }

    @Override
    public void resettoBottom() {
        if (searchResultAdapter.getFooterLayoutCount() > 0) {
            return;
        }
        TextView tvBottom = new TextView(this);
        tvBottom.setText("我也是有底线的");
        //刷新界面
        searchResultAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        searchResultAdapter.getLoadMoreModule().loadMoreComplete();
        //关闭刷新
        searchResultAdapter.getLoadMoreModule().loadMoreEnd();
        // 关闭的提醒刷新
        refreshLayoutSearchView.finishRefresh();
    }

    @Override
    public void resetHideNoData() {
        Toast.makeText(this, "搜索结果为空", Toast.LENGTH_SHORT).show();
        refreshLayoutSearchView.finishRefresh();
        showRecommend();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 显示搜索结果视图
     */
    public void showSearch() {
        clSearchNoData.setVisibility(View.GONE);

        // 关闭的提醒刷新
        refreshLayoutSearchView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示推荐搜索视图
     */
    public void showRecommend() {
        clSearchNoData.setVisibility(View.VISIBLE);

        rvSearchResponse.setVisibility(View.GONE);
    }

    /**
     * 执行搜索操作
     *
     * @param content 搜索内容
     */
    public void startSearch(String content) {
        if (content.isEmpty()) {
            // 搜索内容为空
            showRecommend();
            Toast.makeText(this, "搜索内容为空", Toast.LENGTH_SHORT).show();
        } else {
            showSearch();
            // 光标移至末尾
            etSearchInput.setSelection(content.length());
            presenter.getSearchResult().clear();
            // 执行搜索
            presenter.handleSearchResult(etSearchInput.getText().toString().trim(), pageindex);
        }
    }

    @Override
    public void endLoad() {
        refreshLayoutSearchView.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void cleanHistory() {
        // 数据刷新
        searchLocalAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        // 清空
        super.onDestroy();
    }
}
