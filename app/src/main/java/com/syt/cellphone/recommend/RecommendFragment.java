package com.syt.cellphone.recommend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.util.HttpUtil;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 推荐的 碎片
 */
public class RecommendFragment extends Fragment {

    private List<PhoneRecommend> recommendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    /**
     * 获取推荐列表数据
     */
    private void getrecommend() {

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return HttpUtil.getUrl("/recommend/list");
            }
        };
        Future future = Executors.newFixedThreadPool(1).submit(callable);
        try {
            // 获取返回的结果
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuffer response;
        Thread t = new Thread(() -> HttpUtil.getUrl("/recommend/list"));
        t.start();
    }
}
