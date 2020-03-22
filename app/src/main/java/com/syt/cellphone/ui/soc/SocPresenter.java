package com.syt.cellphone.ui.soc;

import android.util.Log;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.pojo.SocList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：syt Date: 2019-12-12
 * 作用: soc主持
 */
public class SocPresenter extends BasePresenter<SocView> {

    /**
     * pageNum soc页号 线程安全
     * socLists 显示的数据
     * conditions 查询条件 默认为空
     * screenSocList 查询结果数据
     */
    private AtomicInteger pageNum   = new AtomicInteger(1);
    private List<Soc> socLists      = Collections.synchronizedList(new ArrayList<>());
    private String conditions       = "";
    private List<Soc> screenSocList = Collections.synchronizedList(new ArrayList<>());
    private final static String TAG = "SocPresenter";

    SocPresenter(SocView socView) {
        super(socView);
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    /**
     * 网络获取soc列表
     * @param  topOrBottom 顶部0 尾部1
     */
    protected void getNetSocList(final boolean topOrBottom) {

        // 顶部 页码还原为1 内容清空
        if (!topOrBottom) {
            pageNum.set(1);
            socLists.clear();
        }

        addDisposable(apiServer.getNetListSoc(pageNum.get()), new BaseObserver<SocList>(baseView) {

            @Override
            public void onSuccess(SocList socList) {
                //SocList 是接口数据
                Log.d(TAG, "onSuccess: " + socList.getPageSize());
                socLists.addAll(socList.getList());

                MyApp.getDaoSession().getSocDao().insertOrReplaceInTx(socList.getList());
                // 结果筛选
                SearchSocList();
                // 没有内容就再一次请求
                if (screenSocList.size() == 0) {
                    getNetSocList(true);
                }
                if (socList.getPageSize() >= pageNum.get()) {
                    // 记录页码 +0
                    pageNum.incrementAndGet();
                    //刷新主界面 的soc列表
                    baseView.refreshRv();
                } else {
                    //显示到底了
                    baseView.showNoData();
                    // 然后重新刷新
//                    pageNum = 1;
                }
            }

            @Override
            public void onError(String msg) {
                Log.d(TAG, "onError: " + msg);
            }
        }, 0);

    }

    /**
     * 按照条件查询结果
     */
    public void SearchSocList() {
        screenSocList.clear();
        for (Soc soc : socLists) {
            if (soc.getSocName().indexOf(conditions) != -1
                    || soc.getSocTrademark().indexOf(conditions) != -1 ) {
                screenSocList.add(soc);
            }
        }
    }

    /**
     * 获取筛选结果
     * @return 查询结果soc列表
     */
    public List<Soc> getScreenSocList() {
        return screenSocList;
    }
}
