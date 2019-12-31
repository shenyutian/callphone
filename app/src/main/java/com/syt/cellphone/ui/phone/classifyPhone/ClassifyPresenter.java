package com.syt.cellphone.ui.phone.classifyPhone;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneRecommend;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shenyutian
 * @data 2019-12-24 14:53
 * 功能 分类数据指挥
 */
public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    /**
     * item 这个碎片的类别
     * phoneBaseList 数据集
     */
    private String item;
    private List<PhoneBase> phoneBaseList = new LinkedList<>();

    public ClassifyPresenter(ClassifyView classifyView, String item) {
        super(classifyView);
        this.item = item;
    }

    public List<PhoneBase> getPhoneBaseList() {
        return phoneBaseList;
    }

    /**
     * @param b phone数据列表
     */
    public void getNetSocList(boolean b) {

        addDisposable(apiServer.getClassifyPhone(item), new BaseObserver<List<PhoneBase>>(baseView) {

            @Override
            public void onSuccess(List<PhoneBase> o) {
                phoneBaseList.addAll(o);
                baseView.refreshRv();
            }

            @Override
            public void onError(String msg) {

            }
        }, 0);
    }

    /**
     * 请求推荐列表 = 轮播图
     */
    public void getNetBanner() {
        addDisposable(apiServer.getRecommendList(), new BaseObserver<List<PhoneRecommend>>(baseView) {

            @Override
            public void onSuccess(List<PhoneRecommend> phoneRecommends) {
                baseView.showHeaderView(phoneRecommends);
            }

            @Override
            public void onError(String msg) {

            }
        }, 0);
    }
}
