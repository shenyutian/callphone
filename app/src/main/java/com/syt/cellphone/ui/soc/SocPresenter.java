package com.syt.cellphone.ui.soc;

import android.util.Log;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.pojo.SocList;

import java.util.LinkedList;
import java.util.List;

/**
 * @author：syt Date: 2019-12-12
 * 作用: soc主持
 */
public class SocPresenter extends BasePresenter<SocView> {

    /**
     * socNum soc页号
     * socLists 显示的数据
     */
    private volatile int pageNum = 1;
    private List<Soc> socLists = new LinkedList<>();
    private final static String TAG = "SocPresenter";

    SocPresenter(SocView socView) {
        super(socView);
    }

    /**
     * 获取soc列表
     * @return 全部soc
     */
    public List<Soc> getSocList() {

        //查询数据库里面的soc数据

        return socLists;
    }

    /**
     * 网络获取soc列表
     * @param  topOrBottom 顶部0 尾部1
     */
    protected void getNetSocList(final boolean topOrBottom) {

        // 顶部 页码还原为1 内容清空
        if (!topOrBottom) {
            pageNum = 1;
            socLists.clear();
        }

        addDisposable(apiServer.getNetListSoc(pageNum), new BaseObserver<SocList>(baseView) {

            @Override
            public void onSuccess(SocList socList) {
                //SocList 是接口数据
                Log.d(TAG, "onSuccess: " + socList.getPageSize());
                socLists.addAll(socList.getList());

                //TODO dao层添加
                Log.d(TAG, "onSuccess: Thread : " + Thread.currentThread().getName());
                MyApp.getDaoSession().getSocDao().insertOrReplaceInTx(socList.getList());

                if (socList.getPageNum() >= pageNum) {
                    //显示到底了
                    baseView.showNoData();
                } else {
                    // 记录页码 +1
                    pageNum++;
                    //刷新主界面 的soc列表
                    baseView.refreshRv();
                }
            }

            @Override
            public void onError(String msg) {
                Log.d(TAG, "onError: " + msg);
            }
        }, 0);

    }
}
