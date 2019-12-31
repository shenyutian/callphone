package com.syt.cellphone.ui.phone.classifyPhone;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneBase;

import java.util.List;

/**
 * @author shenyutian
 * @data 2019-12-24 17:31
 * 功能 分类fragment的适配器
 */
public class ClassifyAdapter extends BaseQuickAdapter<PhoneBase, BaseViewHolder> {
    public ClassifyAdapter(int layoutResId, @Nullable List<PhoneBase> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PhoneBase item) {
        Glide.with(mContext).load(item.getBaseImage()).into((ImageView) helper.getView(R.id.iv_phone_item_img));
        helper.setText(R.id.tv_phone_item_name, item.getBaseName())
                .setText(R.id.tv_phone_item_feature, item.getBaseFeature())
                .setText(R.id.tv_phone_item_price, item.getBasePrice());
    }
}
