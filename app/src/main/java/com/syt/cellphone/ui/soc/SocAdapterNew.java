package com.syt.cellphone.ui.soc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.Soc;

import java.util.List;

/**
 * @author：syt Date: 2019-12-17
 * 作用: soc新的适配器
 */
public class SocAdapterNew extends BaseQuickAdapter<Soc, BaseViewHolder> {


    public SocAdapterNew(int layoutResId, @Nullable List<Soc> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Soc item) {
        // 写入数据
        helper.setText(R.id.soc_text_name, item.getSocName())
                .setText(R.id.soc_text_trademark, item.getSocTrademark())
                .addOnClickListener(R.id.soc_lay_item);
    }
}
