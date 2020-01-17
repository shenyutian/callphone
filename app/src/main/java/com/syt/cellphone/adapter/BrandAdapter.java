package com.syt.cellphone.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneTrademark;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-08 11:47
 * 功能 品牌列表适配器
 */
public class BrandAdapter extends BaseQuickAdapter<PhoneTrademark, BaseViewHolder> implements LoadMoreModule {

    public BrandAdapter(int layoutResId, @Nullable List<PhoneTrademark> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PhoneTrademark item) {
        Glide.with(getContext()).load(item.getTrademarkLog()).into((ImageView) helper.getView(R.id.iv_brand_picture));
        helper.setText(R.id.tv_brand_name, item.getTrademarkName());
    }
}
