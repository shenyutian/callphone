package com.syt.cellphone.ui.phone.classifyPhone;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;

/**
 * @author shenyutian
 * @data 2019-12-24 14:52
 * 功能 分类的fragment
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyView {

    @BindView(R.id.rv_phone_classify_list)
    RecyclerView rvPhoneClassifyList;
    @BindView(R.id.srl_phone_classify_handle)
    SwipeRefreshLayout srlPhoneClassifyHandle;
    @BindView(R.id.tv_phone_bottom)
    TextView tvPhoneBottom;
    private String titleName;
    private int item;
    private ClassifyAdapter classifyAdapter;

    /**
     * 暴露给外部的方法
     *
     * @param item 指定页面的属性
     * @return 设置好的fragment
     */
    public static ClassifyFragment newInstance(String titleName, final int item) {
        //传输数据的容器 k-v保存
        Bundle bundle = new Bundle();
        bundle.putInt("item", item);
        bundle.putString("titleName", titleName);
        ClassifyFragment classifyFragment = new ClassifyFragment();
        // 传递参数
        classifyFragment.setArguments(bundle);
        return classifyFragment;
    }

    @Override
    protected ClassifyPresenter initPresenter() {
        return new ClassifyPresenter(this, titleName);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_classify;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            titleName = getArguments().getString("titleName");
            item = getArguments().getInt("item");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {
        //导入数据 滑动监听
        initRv();
        if (item == 0) {
            fpresenter.getNetBanner();
        }
        // 底部显示
        tvPhoneBottom.setText(titleName);
    }

    private void initRv() {
        // 设置布局方式
        rvPhoneClassifyList.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        classifyAdapter = new ClassifyAdapter(R.layout.item_phone, fpresenter.getPhoneBaseList());
        // 点击事件
        classifyAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View view, int position) -> {

        });
        // 上拉事件
        classifyAdapter.setOnLoadMoreListener(() ->
                        fpresenter.getNetSocList(true)
                , rvPhoneClassifyList);
        // 执行适配器
        rvPhoneClassifyList.setAdapter(classifyAdapter);
        // 下拉刷新
        srlPhoneClassifyHandle.setOnRefreshListener(() ->
            fpresenter.getNetSocList(false)
        );
    }

    @Override
    public void refreshRv() {
        //刷新界面
        classifyAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        classifyAdapter.loadMoreComplete();
        // 关闭上面的刷新
        srlPhoneClassifyHandle.setRefreshing(false);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void onErrorCode(BaseBean model) {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 加载上面的轮播图
     */
    @Override
    public void showHeaderView(List<PhoneRecommend> recommends) {
        if (recommends == null || recommends.size() == 0) {
            return;
        }
        //标题 + 图片url
        List<String> titles;
        List<String> imgurls;

        Banner banner = new Banner(context);
        // 如果api高，就用steam。不行就用老方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            titles = recommends.stream().map(e -> e.getPhoneId()+"").collect(Collectors.toList());
            banner.setBannerTitles(titles);

            imgurls = recommends.stream().map(PhoneRecommend::getRecommendReimage).collect(Collectors.toList());
            banner.setImages(imgurls);
        } else {
            titles = new LinkedList<>();
            imgurls = new LinkedList<>();
            for (PhoneRecommend recommend : recommends) {
                titles.add(recommend.getPhoneId()+"");
                imgurls.add(recommend.getRecommendReimage());
            }
//            banner.setBannerTitles(titles);
            banner.setImages(imgurls);
        }
        banner.setOnBannerListener(position ->
            ToastUtil.makeText("点击了 " + position)
        );
        //设置轮播图加载方式
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
        LogUtil.d("高度: " + banner.getHeight());
        classifyAdapter.addHeaderView(banner, 0);

        
    }


}
