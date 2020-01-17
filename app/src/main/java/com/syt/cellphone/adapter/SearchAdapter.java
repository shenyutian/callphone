package com.syt.cellphone.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.SearchHistory;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-14 16:15
 * 功能 搜索推荐+搜索历史适配器
 */
public class SearchAdapter extends BaseQuickAdapter<SearchHistory, BaseViewHolder> {

    public SearchAdapter(int layoutResId, @Nullable List<SearchHistory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchHistory item) {
        helper.setText(R.id.tv_search_text, item.getSearchContent());
    }
}
