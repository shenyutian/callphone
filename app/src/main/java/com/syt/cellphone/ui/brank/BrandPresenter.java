package com.syt.cellphone.ui.brank;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneTrademark;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-08 18:36
 * 功能 品牌数据控制类
 */
public class BrandPresenter extends BasePresenter<BrandView> {

    /**
     * -----------------------数据集-----------------------------
     * trademarkList            品牌数据
     */
    private List<PhoneTrademark> trademarkList = new ArrayList<>();

    public BrandPresenter(BrandView baseView) {
        super(baseView);
    }

    public List<PhoneTrademark> getTrademarkList() {
        return trademarkList;
    }

    /**
     * 刷新数据
     */
    public void refreshData() {

        // 判定网络 如果网络不行 本地数据库加载
        if (false) {
            return;
        }

        addDisposable(apiServer.getAllTrademark(), new BaseObserver<List<PhoneTrademark>>(baseView) {
            @Override
            public void onSuccess(List<PhoneTrademark> o) {
                trademarkList.addAll(o);
                baseView.showBrandData();
            }

            @Override
            public void onError(String msg) {

            }
        }, 0);

    }
}
