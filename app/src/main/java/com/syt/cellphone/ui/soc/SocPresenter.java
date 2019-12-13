package com.syt.cellphone.ui.soc;

import android.util.Log;

import com.syt.cellphone.base.BasePresenter;
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
     * socLists 显示的数据
     */
    private List<Soc> socLists = new LinkedList<>();
    private final static String TAG = "SocPresenter";

    public SocPresenter(SocView socView) {
        super(socView);
    }

    public List<Soc> getSocList() {
        return socLists;
    }

    /**
     * 网络获取soc列表
     * @param pageNum 页号
     */
    protected void getNetSocList(int pageNum) {

        addDisposable(apiServer.getNetListSoc(pageNum), new BaseObserver<SocList>(baseView) {

            @Override
            public void onSuccess(SocList socList) {
                Log.d(TAG, "onSuccess: " + socList.getPageSize());
                socLists.addAll(socList.getList());

                //TODO dao层添加
                Log.d(TAG, "onSuccess: Thread : " + Thread.currentThread().getName());

                //刷新主界面
                baseView.refreshRv();
            }

            @Override
            public void onError(String msg) {
                Log.d(TAG, "onError: " + msg);
            }
        }, 0);




    }
}
