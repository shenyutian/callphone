package com.syt.cellphone.ui.phone;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.service.PhoneService;

import java.util.ArrayList;
import java.util.List;

public class PhoneFragment extends Fragment {

    private List<PhoneBase> baseList = new ArrayList<>();
    private Context mContext;
    private static final int SUCCESS = 1;
    private static RecyclerView recyclerView;
    private PhoneAdapter baseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getPhoneList();
        return inflater.inflate(R.layout.fragment_phone, container, false);
    }

    // recyclerView 使用插入
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        // 注册 RecyclerView
        recyclerView= (RecyclerView) getActivity().findViewById(R.id.phone_recycler_view);
        // 设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        baseAdapter = new PhoneAdapter(baseList, getContext());
        // 执行适配器
        recyclerView.setAdapter(baseAdapter);
    }

    /**
     * 获取数据
     */
    private void getPhoneList() {
        new Thread(() -> {
            baseList.addAll(PhoneService.getListBaseByid(1));
            Message message = new Message();
            message.what = SUCCESS;
            handler.sendMessage(message);
        }).start();
    }

    /**
     * 对ui操作的handle
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    baseAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

}
