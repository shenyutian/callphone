package com.syt.cellphone.ui.phone.search;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.greendao.SearchHistoryDao;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneBasePageList;
import com.syt.cellphone.pojo.SearchHistory;
import com.syt.cellphone.util.ToastUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenyutian
 * @data 2020-01-09 18:54
 * 功能 搜索结果控制
 */
public class SearchPresener extends BasePresenter<SearchView> {

    /**
     * -------------------参数说明----------------
     *  searchInput             搜索参数
     *  pageNum                 页数
     *  searchResult            搜索结果集,加入同步代码块，来保证线程安全
     *  searchHistoryDao        搜索历史的dao操作类
     *  searchHistoryList       搜索历史集合
     */
    private String              searchInput         =   null;
    private AtomicInteger       pageNum             =   new AtomicInteger(0);
    private List<PhoneBase>     searchResult        =   Collections.synchronizedList(new LinkedList<>());
    private SearchHistoryDao    searchHistoryDao    =   MyApp.getDaoSession().getSearchHistoryDao();
    private List<SearchHistory> searchHistoryList   =   null;

    public SearchPresener(SearchView baseView) {
        super(baseView);
    }

    /**
     * 搜索结果
     */
    public List<PhoneBase> getSearchResult() {
        return searchResult;
    }

    /**
     * 搜索历史
     */
    public List<SearchHistory> getSearchHistoryList() {
        searchHistoryList = searchHistoryDao.loadAll();
        return searchHistoryList;
    }

    /**
     * 搜索结果 处理
     * @param searchInput 输入的内容
     */
    public void handleSearchResult(String searchInput) {
        this.searchInput = searchInput;
        addDisposable(apiServer.getClassifyPhone(pageNum.get(), searchInput), new BaseObserver<PhoneBasePageList>(baseView) {
            @Override
            public void onSuccess(PhoneBasePageList o) {
                searchResult.addAll(o.getList());
                // 原子+1
                pageNum.incrementAndGet();
                if (pageNum.get() >= o.getPageSize()) {
                    baseView.resettoBottom();
                    return;
                }
                // 数据库保存搜索历史
                SearchHistory searchHistory = new SearchHistory(null, searchInput, System.currentTimeMillis());
                searchHistoryDao.insertOrReplace(searchHistory);
                //刷新页面
                baseView.resetSearchRv();
            }

            @Override
            public void onError(String msg) {
                ToastUtil.makeText("搜索数据加载失败");
            }
        }, 0);
    }
}