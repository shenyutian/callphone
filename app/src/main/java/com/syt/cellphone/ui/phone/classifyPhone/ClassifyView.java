package com.syt.cellphone.ui.phone.classifyPhone;

import com.syt.cellphone.base.BaseView;
import com.syt.cellphone.pojo.PhoneRecommend;

import java.util.List;

/**
 * @author shenyutian
 * @data 2019-12-24 14:53
 * 功能 分类视图
 */
public interface ClassifyView extends BaseView {

    /**
     * 刷新视图列表
     */
    void refreshRv();

    /**
     * 显示没有数据 = 我也是有底线的。
     */
    void showNoData();

    /**
     * 显示上面的视图
     * 主要用于给首页显示轮播图
     * @param recommends 轮播图数据
     */
    void showHeaderView(List<PhoneRecommend> recommends);
}
