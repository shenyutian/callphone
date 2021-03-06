package com.syt.cellphone.ui.phone.search;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.greendao.PhoneRecommendDao;
import com.syt.cellphone.greendao.SearchHistoryDao;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneBasePageList;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.SearchHistory;
import com.syt.cellphone.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-09 18:54
 * 功能 搜索结果控制
 */
public class SearchPresener extends BasePresenter<SearchView> {

    /**
     * -------------------参数说明----------------
     *  searchInput             搜索参数
     *  searchResult            搜索结果集,加入同步代码块，来保证线程安全
     *  recommendDao            推荐搜索dao操作类
     *  searchHistoryDao        搜索历史的dao操作类
     *  searchRecommend         搜索推荐集合
     *  searchHistoryList       搜索历史集合
     */
    private String              searchInput         =   "";
    private List<PhoneBase>     searchResult        =   Collections.synchronizedList(new ArrayList<>());
    private SearchHistoryDao    searchHistoryDao    =   MyApp.getDaoSession().getSearchHistoryDao();
    private PhoneRecommendDao   recommendDao        =   MyApp.getDaoSession().getPhoneRecommendDao();
    private List<SearchHistory> searchRecommends    =   new ArrayList<>();
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
     * 搜索推荐
     * @return 推荐列表
     */
    public List<SearchHistory> getSearchRecommendList() {
        searchRecommends.clear();
        List<PhoneRecommend> phoneRecommends = recommendDao.loadAll();
        SearchHistory searchHistory;
        for (PhoneRecommend phoneRecommend : phoneRecommends) {
            searchHistory = new SearchHistory();
            searchHistory.setSearchContent(phoneRecommend.getRecommendName());
            searchRecommends.add(searchHistory);
        }
        return searchRecommends;
    }

    /**
     * 搜索历史
     */
    public List<SearchHistory> getSearchHistoryList() {
        searchHistoryList = searchHistoryDao.loadAll();
        // 倒置List
        Collections.reverse(searchHistoryList);
        return searchHistoryList;
    }

    /**
     * 搜索结果 处理
     * @param searchInput 输入的内容
     */
    public void handleSearchResult(String searchInput, int pageNum) {
        // 两次搜索内容不一样  数据清空
        if (searchInput != null) {
            if (!this.searchInput.equals(searchInput)) {
                searchResult.clear();
            }
        }
        // 如果页码为1  清空列表
        if (pageNum == 1) {
            searchResult.clear();
        }

        // 保存搜索记录
        this.searchInput = searchInput;

        addDisposable(apiServer.getClassifyPhone(pageNum, searchInput), new BaseObserver<PhoneBasePageList>(baseView) {
            @Override
            public void onSuccess(PhoneBasePageList o) {
                // 数据库保存搜索历史
                SearchHistory searchHistory = new SearchHistory(null, searchInput, System.currentTimeMillis());
                searchHistoryDao.insertOrReplace(searchHistory);
                if (o.getTotal() == 0) {
                    // 搜索结果为空
                    baseView.resetHideNoData();
                    return;
                }
                if (pageNum > o.getPageSize()) {
                    // 加载到底了
//                    baseView.resettoBottom();
                    baseView.endLoad();
                    return;
                    // 超过了就重新加载，省的没有内容难看
//                    pageNum.set(0);
                }
                searchResult.addAll(o.getList());
                //正常的刷新页面
                baseView.resetSearchRv();
            }

            @Override
            public void onError(String msg) {
                ToastUtil.makeText("搜索数据加载失败");
                baseView.resetHideNoData();
            }
        }, 0);
    }

    public void handCleanHistory() {
        // 清空历史
        searchHistoryDao.deleteAll();
        searchHistoryList.clear();
        // 刷新页面
        baseView.cleanHistory();
    }
}
