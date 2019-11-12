package com.syt.cellphone.phone;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.service.PhoneService;
import java.util.List;

public class PhoneFragment extends Fragment {

    private List<PhoneBase> baseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getPhoneList();
        return inflater.inflate(R.layout.fragment_phone, container, false);
    }

    // recyclerView 使用插入
    @Override
    public synchronized void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 注册 RecyclerView
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.phone_recycler_view);
        // 设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        PhoneAdapter baseAdapter = new PhoneAdapter(baseList, getContext());
        // 执行适配器
        recyclerView.setAdapter(baseAdapter);
    }

    /**
     * 获取数据
     */
    private synchronized void getPhoneList() {
        Thread t = new Thread(new takeThread());
        t.start();
        try {
            t.join();
        }catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    //数据线程
    class takeThread extends Thread {
        @Override
        public void run() {
            baseList = PhoneService.getListBaseByid(1);
        }
    }

}
