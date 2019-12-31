package com.syt.cellphone.ui.update;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.service.PhoneService;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 主页，最近更新界面。
 */
public class UpdateFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //链式列表，方便增加，修改数据 显示的数据
    private volatile List<PhoneBase> phoneBaseList = new LinkedList<>();
    //推荐栏，轮播图数据
    private volatile List<PhoneRecommend> phoneRecommendList = new ArrayList<>();
    // 数据页号，加载了0以后，加载第2页数据
    private int count = 1;
    // 页数，记录最大加载数
    private volatile static int pageNum;
    private UpdateAdapter updateAdapter; // 适配器

    // 布局
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    // 创建视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getPagenum();
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        initLayout(view);
        return view;
    }

    /**
     * 布局元素数据
     */
    private void initLayout(View view) {
        view.canScrollVertically(1);
        // 注册布局
        swipeRefreshLayout = view.findViewById(R.id.update_swipe_view);
        recyclerView = view.findViewById(R.id.update_recycler_view);
        // 环境中加入 布局
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // 写入数据
        updateAdapter = new UpdateAdapter(R.layout.item_phone, phoneBaseList);
        //设置RecyclerView条目点击事件
        updateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PhoneBase phoneBase = updateAdapter.getItem(position);
                Toast.makeText(view.getContext(), "点击编号 " + phoneBase.getBaseId() + ",名称: " + phoneBase.getBaseName(), Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 上拉 事件
         */
        updateAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("shenyutian", "onLoadMoreRequested: " + count);
                if (count <= pageNum) {
                    getBaseList();
                    updateAdapter.setNewData(phoneBaseList);
//                    updateAdapter.loadMoreComplete();
//                    updateAdapter.loadMoreComplete(); //完全刷新布局
//                    updateAdapter.loadMoreFail(); // 持续更新
                    updateAdapter.loadMoreEnd(); // 底部插入
                } else {
//                    updateAdapter.addFooterView(); // 底部插入视图
                }
            }
        }, recyclerView);
        recyclerView.setAdapter(updateAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    /**
     * 下拉更新 数据操作
      */
    @Override
    public void onRefresh() {
        phoneBaseList.clear();
        getBaseList();
        Log.e("shenyutian", "onRefresh: " + count);
        updateAdapter.setNewData(phoneBaseList);
        updateAdapter.loadMoreComplete();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 获取数据,从底部插入
      */
    private void getBaseList() {
        Thread phoneThread = new phoneThread();
        phoneThread.start();
        try {
            // join() 通常用于在main()主线程内，等待其它线程完成再结束main()主线程
            phoneThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *   自定义数据线程
      */
    public class phoneThread extends Thread {
        @Override
        public void run() {
            phoneBaseList.addAll(PhoneService.getListBaseByid(count));
            count++;
        }
    }

    // 获取页数，暂时为静态
    public void getPagenum() {
        pageNum = 80;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                pageNum = PhoneService.getPageNum();
            }
        });
        t.start();
    }

    /**
     * 获取推荐轮播图
     */
    public void getRecommend() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                phoneRecommendList = PhoneService.getRecommend();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *     加载图片banner轮播图图片
     */
    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load((String) path).into(imageView);
        }
    }

}
