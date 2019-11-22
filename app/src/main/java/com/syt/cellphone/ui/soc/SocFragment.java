package com.syt.cellphone.ui.soc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syt.cellphone.R;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.service.SocService;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * soc 处理器 列表
 */
public class SocFragment extends Fragment {

    // soc 数据
    private List<Soc> socList;
    // 数据状态码
    private int state = 500;

    // 将视图 fragment 写入活动中
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 注册 RecyclerView
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_soc_list);
        // 设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        SocAdapter socAdapter = new SocAdapter(socList, getContext());
        // 执行适配器
        recyclerView.setAdapter(socAdapter);
    }

    // 建立视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 获取数据
        getSocList();
        return inflater.inflate(R.layout.fragment_soc, container, false);
    }

    /**
     * 获取数据
     */
    private void getSocList() {

        // 判断 SQLite 是否有
        if (LitePal.findAll(Soc.class).size() > 0) {
            // 存在就加载本地的
            socList = LitePal.findAll(Soc.class);
        } else {
            // 不存在就网络获取
            socList = new ArrayList<>();
            Thread t = new Thread(() -> socList = SocService.getSocList(1));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 保存 网络数据到 SqLite
            LitePal.saveAll(socList);
        }

    }

}
