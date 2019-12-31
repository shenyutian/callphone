package com.syt.cellphone.ui.update;

import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneBase;

import java.util.List;

public class UpdateAdapter extends BaseQuickAdapter<PhoneBase, BaseViewHolder> {

    public UpdateAdapter(int layoutResId, @Nullable List<PhoneBase> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PhoneBase item) {
        int adapterPosition = helper.getAdapterPosition();
        if (adapterPosition % 2 == 0) {
            helper.setBackgroundColor(R.id.phone_recycler_item, Color.WHITE);
        } else {
            helper.setBackgroundColor(R.id.phone_recycler_item, Color.WHITE);
        }
        // 写入数据
        Glide.with(mContext).load(item.getBaseImage()).into((ImageView) helper.getView(R.id.phone_recycler_item));
        helper.setText(R.id.tv_phone_item_name, item.getBaseName());
        helper.setText(R.id.tv_phone_item_feature, item.getBaseFeature());
        helper.setText(R.id.tv_phone_item_price, item.getBasePrice());
    }

}
