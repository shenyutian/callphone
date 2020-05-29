package com.syt.cellphone.ui.phone.classifyPhone;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.greendao.PhoneRecommendDao;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneBasePageList;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenyutian
 * @data 2019-12-24 14:53
 * 功能 分类数据指挥
 */
public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    /**
     * item 这个碎片的类别 主页 最近更新 小米 华为 ....
     * phoneBaseList 数据集
     * pageNum 页号
     */
    private String item;
    private List<PhoneBase> phoneBaseList = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger pageNum = new AtomicInteger(1);
    private PhoneRecommendDao recommendDao = MyApp.getDaoSession().getPhoneRecommendDao();

    public ClassifyPresenter(ClassifyView classifyView, String item) {
        super(classifyView);
        this.item = item;
    }

    public List<PhoneBase> getPhoneBaseList() {
        return phoneBaseList;
    }

    /**
     * phone数据列表
     * @param topOrBottom 顶部0 尾部1
     */
    public void getNetPhoneList(boolean topOrBottom) {

        // 顶部 页码还原为1 内容清空
        if (!topOrBottom) {
            pageNum.set(1);
            phoneBaseList.clear();
        }

        switch (item) {
            case "最近更新":
                getUpdatePhone();
                break;
            case "首页":
                // 轮播图
                getNetBanner();
                // 首页数据
                getHomePhone();
                break;
            default:
                getNetClassifyPhone();
                break;
        }
    }

    /**
     * 获得最近更新的数据
     */
    private void getHomePhone() {
        addDisposable(apiServer.getBasePhone(pageNum.get() + 5), new BaseObserver<PhoneBasePageList>(baseView) {

            @Override
            public void onSuccess(PhoneBasePageList o) {
                toDealWithData(o);
            }

            @Override
            public void onError(String msg) {
                baseView.refreshRv();
                ToastUtil.makeText(msg);
            }
        }, 0);
    }

    /**
     * 获得最近更新的数据
     */
    private void getUpdatePhone() {
        addDisposable(apiServer.getBasePhone(pageNum.get()), new BaseObserver<PhoneBasePageList>(baseView) {

            @Override
            public void onSuccess(PhoneBasePageList o) {
                toDealWithData(o);
            }

            @Override
            public void onError(String msg) {
                baseView.refreshRv();
                ToastUtil.makeText(msg);
            }
        }, 0);
    }

    /**
     * 获取分类的数据
     */
    private void getNetClassifyPhone() {

        addDisposable(apiServer.getClassifyPhone(pageNum.get(), item), new BaseObserver<PhoneBasePageList>(baseView) {

            @Override
            public void onSuccess(PhoneBasePageList o) {
                toDealWithData(o);
            }

            @Override
            public void onError(String msg) {
                baseView.refreshRv();
                ToastUtil.makeText(msg);
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
                // 保存推荐列表 后面的搜索推荐也要用
                recommendDao.insertOrReplaceInTx(phoneRecommends);
                // 刷新主页推荐轮播图
                baseView.showHeaderView(phoneRecommends);
            }

            @Override
            public void onError(String msg) {
                baseView.showHeaderView(recommendDao.loadAll());
            }
        }, 0);
    }

    /**
     * 处理数据
     */
    private void toDealWithData(PhoneBasePageList data) {
        if (data == null) {
            return;
        }
        if (pageNum.get() >= data.getPageSize()) {
            baseView.showNoData();
            return;
        }
        phoneBaseList.addAll(data.getList());
        baseView.refreshRv();
        // 记录页码 +1
        pageNum.incrementAndGet();
    }
}
