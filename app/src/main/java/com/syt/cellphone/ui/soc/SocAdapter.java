package com.syt.cellphone.ui.soc;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.syt.cellphone.R;
import com.syt.cellphone.pojo.Soc;

import java.util.List;
import java.util.Objects;

/**
 * @author shenyutian
 */
public class SocAdapter extends RecyclerView.Adapter<SocAdapter.ViewHolder> {

    private List<Soc> socList; // soc列表
    private Context soc_context; // 内容

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View socView;
        TextView socName; // 名称
        TextView socTrademark;// 品牌

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            socName = itemView.findViewById(R.id.soc_text_name);
            socTrademark = itemView.findViewById(R.id.soc_text_trademark);
            socView = itemView;
        }
    }

    public SocAdapter(List<Soc> socList, Context context) {
        this.socList = socList;
        soc_context = context;
    }

    /**
     * 单行数据写入
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soc_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.socView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Soc soc = socList.get(position);
                Toast.makeText(v.getContext(), "点击编号 " + soc.getSocId() + ",名称: " + soc.getSocName(), Toast.LENGTH_SHORT).show();
                dialogSoc(soc.getSocId(), null);
//                Blurry.with(soc_context).radius(10).sampling(2).onto();
                // 为点击事件 跳转 并且传递参数socId。
//                Intent intent = new Intent(SytMainActivity.ACTIVITY_SERVICE);
//                intent.putExtra("socId", soc.getSocId());
//                soc_context.startActivity(intent);

            }
        });
        return viewHolder;
    }

    /**
     * 写入数据到列表中，
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //写入数据
        Soc soc = socList.get(position);//拿到数据  根据id
        holder.socName.setText(soc.getSocName());
        holder.socTrademark.setText(soc.getSocTrademark());
    }

    @Override
    public int getItemCount() {
        return socList.size();
    }


    private void dialogSoc(long socId, final View view) {
        // 弹窗创建
        final AlertDialog toastDialog = new AlertDialog.Builder(soc_context, R.style.DialogStyle).create();
        toastDialog.show();
        Window window = toastDialog.getWindow();
        Objects.requireNonNull(window).setGravity(Gravity.CENTER);
    }
}
