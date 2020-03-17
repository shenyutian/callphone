package com.syt.cellphone.ui.phone.classifyPhone;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.syt.cellphone.R;
import com.syt.cellphone.adapter.PhoneBaseAdapter;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.ui.phone.details.PhoneDetailsActivity;
import com.syt.cellphone.util.ToastUtil;
import com.syt.cellphone.widget.MyBanner;
import com.youth.banner.loader.ImageLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;

/**
 * @author shenyutian
 * @data 2019-12-24 14:52
 * 功能 分类的fragment 就是开始的第一个界面主页
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyView {

    /**
     * --------------黄油刀的布局id注册------------
     */
    @BindView(R.id.rv_phone_classify_list)
    RecyclerView rvPhoneClassifyList;
    @BindView(R.id.srl_phone_classify_handle)
    SwipeRefreshLayout  srlPhoneClassifyHandle;
    @BindView(R.id.tv_phone_bottom)
    TextView            tvPhoneBottom;

    /**
     * --------------页面参数-----------------
     * titleName        标题名称 例如首页，最近更新.....
     * classifyAdapter  数据列表适配器
     */
    private String          titleName;
    private int             item;
    private PhoneBaseAdapter classifyAdapter;

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
        fpresenter.getNetPhoneList(true);
        // 底部显示
        tvPhoneBottom.setText(titleName);
    }

    private void initRv() {
        // 设置布局方式
        rvPhoneClassifyList.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        classifyAdapter = new PhoneBaseAdapter(R.layout.item_phone, fpresenter.getPhoneBaseList());
        // 上拉事件
        classifyAdapter.getLoadMoreModule().setOnLoadMoreListener(
                () -> fpresenter.getNetPhoneList(true));
        // rv点击事件
        classifyAdapter.setOnItemClickListener(
                (adapter, view, position) -> {
                        ToastUtil.makeText(fpresenter.getPhoneBaseList().get(position).getBaseName());
                        // 进行跳转
                        Intent startPhoneDetails = new Intent(getContext(), PhoneDetailsActivity.class);
                        startPhoneDetails.putExtra("phoneId", fpresenter.getPhoneBaseList().get(position).getBaseId());
                        getContext().startActivity(startPhoneDetails);
                    }
                );
        // 执行适配器
        rvPhoneClassifyList.setAdapter(classifyAdapter);
        // 下拉刷新 todo 会导致崩溃
        srlPhoneClassifyHandle.setOnRefreshListener(() -> {
                    fpresenter.getNetPhoneList(false);
                }
        );
    }

    @Override
    public void refreshRv() {
        //刷新界面
        classifyAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        classifyAdapter.getLoadMoreModule().loadMoreComplete();
        // 关闭上面的刷新
        srlPhoneClassifyHandle.setRefreshing(false);
    }

    /**
     * 显示我已经到底了
     */
    @Override
    public void showNoData() {
        if (classifyAdapter.getFooterLayoutCount() > 0) {
            return;
        }
        TextView tvBottom = new TextView(getContext());
        tvBottom.setText("我也是有底线的");
        //刷新界面
        classifyAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        classifyAdapter.getLoadMoreModule().loadMoreComplete();
        //关闭刷新
        classifyAdapter.getLoadMoreModule().loadMoreEnd();
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
     * 加载首页上面的轮播图
     */
    @Override
    public void showHeaderView(List<PhoneRecommend> recommends) {
        // 推荐数据没有 退出
        if (recommends == null || recommends.size() == 0) {
            return;
        }
        // 顶部有轮播图 就退出
        Log.d("Class", "showHeaderView: " + classifyAdapter.getHeaderLayoutCount());
        if (classifyAdapter.getHeaderLayoutCount() > 0) {
            return;
        }
        //标题 + 图片url
        List<String> titles;
        List<String> imgurls;

        MyBanner banner = new MyBanner(getContext());
//        Banner banner = new Banner(context);
        // 设置轮播图的宽高
        banner.setLayoutParams(new ViewGroup.LayoutParams(getContext().getResources().getDisplayMetrics().widthPixels, 450));
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
                {
                    ToastUtil.makeText("点击了 " + titles.get(position));
                    // 进行跳转
                    Intent startPhoneDetails = new Intent(getContext(), PhoneDetailsActivity.class);
                    startPhoneDetails.putExtra("phoneId", Integer.parseInt(titles.get(position)));
                    context.startActivity(startPhoneDetails);
                }
        );

        //设置轮播图加载方式
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getContext()).load(path).into(imageView);
            }
        }).start();
        classifyAdapter.addHeaderView(banner);

    }
}
