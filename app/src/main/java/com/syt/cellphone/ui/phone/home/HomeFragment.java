package com.syt.cellphone.ui.phone.home;

import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author shenyutian
 * @data 2019-12-24 09:24
 * 功能 主页 fragment
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {


    /**
     * bannerList 轮播图数据
     */
    @BindView(R.id.banner_phone_home_top)
    Banner bannerPhoneHomeTop;
    @BindView(R.id.rv_phone_list)
    RecyclerView rvPhoneList;
    @BindView(R.id.srl_phone_handle)
    SwipeRefreshLayout srlPhoneHandle;
    private List<String> bannerList;

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        bannerList = new ArrayList<>();
        bannerList.add("https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M05/06/05/ChMly113aC-IVGMlAAG11ap9YqUAAXnogBWIakAAbXt993.jpg");
        bannerList.add("https://dg-fd.zol-img.com.cn/t_s2000x2000/g1/M0A/02/03/ChMljl2EabyIPg2aAAFbqHHjP_gAAP3CgAVUUYAAVvA000.jpg");
        bannerList.add("https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M00/09/05/ChMly12LEZWISaMXAADaHXBKTj0AAXzwQDaaa0AANo1024.jpg");
        bannerPhoneHomeTop.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        bannerPhoneHomeTop.setImages(bannerList);
        bannerPhoneHomeTop.start();
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
}
