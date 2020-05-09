package com.syt.cellphone.ui.phone.search;

import com.syt.cellphone.base.BaseView;

/**
 * @author shenyutian
 * @data 2020-01-09 18:52
 * 功能 搜索结果
 */
public interface SearchView extends BaseView {

    /**
     * 刷新搜索结果列表视图
     */
    void resetSearchRv();

    /**
     * 没有数据了，加载底部提示
     */
    void resettoBottom();

    /**
     * 显示搜索结果为空
     */
    void resetHideNoData();
}
