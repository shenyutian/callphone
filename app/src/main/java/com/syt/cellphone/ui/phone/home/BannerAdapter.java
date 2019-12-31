package com.syt.cellphone.ui.phone.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.syt.cellphone.R;

import java.util.List;

/**
 * @author shenyutian
 * @data 2019-12-24 09:42
 * 功能 主页轮播图适配器
 */
public class BannerAdapter extends Adapter<BannerAdapter.BannerViewHolder> {

    private List<String> imageUrls;

    public BannerAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        // 显示图片
        Glide.with(holder.itemView)
                .load(imageUrls.get(position))
                .into(holder.ivBanner);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    protected class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.iv_phone_home_banner);
        }
    }
}
