package com.syt.cellphone.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneBase;

import java.util.List;

/**
 * phoneBase 的适配器  用于 RecyclerView
 */
public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private List<PhoneBase> phoneBaseList;
    private Context context;

    public PhoneAdapter(List<PhoneBase> phoneBaseList, Context context) {
        this.phoneBaseList = phoneBaseList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view; // 视图
        ImageView img; //左边的图片
        TextView name; // base 名称
        TextView feature; // base 详情，介绍
        TextView price; // base 价格
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.phone_item_img);
            name = itemView.findViewById(R.id.phone_item_name);
            feature = itemView.findViewById(R.id.phone_item_feature);
            price = itemView.findViewById(R.id.phone_item_price);
            view = itemView;
        }
    }

    /**
     * 每个item 的视图
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_recycler_item, parent, false);
        final PhoneAdapter.ViewHolder viewHolder = new PhoneAdapter.ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                PhoneBase phoneBase = phoneBaseList.get(position);
                Toast.makeText(v.getContext(), "点击编号 " + phoneBase.getBaseId() + ",名称: " + phoneBase.getBaseName(), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    /**
     * 按照 每个 item 写入数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhoneBase phoneBase = phoneBaseList.get(position);
        Glide.with(context).load(phoneBase.getBaseImage()).into(holder.img);// 写入图片
        holder.name.setText(phoneBase.getBaseName());// 写入 名称
        holder.feature.setText(phoneBase.getBaseFeature()); // 写入 详情介绍
        holder.price.setText(phoneBase.getBasePrice()); // 写入 价格
    }

    @Override
    public int getItemCount() {
        return phoneBaseList.size();
    }

}
