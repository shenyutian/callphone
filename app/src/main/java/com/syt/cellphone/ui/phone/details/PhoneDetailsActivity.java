package com.syt.cellphone.ui.phone.details;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.gyf.immersionbar.ImmersionBar;
import com.syt.cellphone.R;
import com.syt.cellphone.adapter.DetailsAdapter;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.pojo.PhoneConfig;
import com.syt.cellphone.pojo.PhoneFacade;
import com.syt.cellphone.pojo.PhoneShow;
import com.syt.cellphone.pojo.PhotoBean;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.ToastUtil;
import com.syt.cellphone.widget.SytToolBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;

public class PhoneDetailsActivity extends BaseActivity<PhoneDetailsPresenter> implements PhoneDetailsView {

    @BindView(R.id.rv_details_data)
    RecyclerView rvDetailsData;
    @BindView(R.id.bar_details_top)
    SytToolBar barDetailsTop;

    private DetailsAdapter detailsAdapter;

    @Override
    protected PhoneDetailsPresenter createPresenter() {
        return new PhoneDetailsPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_details;
    }

    @Override
    protected void initData() {
        // 沉浸式布局
        ImmersionBar.with(this).init();
        // 列表
        initRv();
        // 请求数据
        presenter.handlePhoneDetails(1293901);
    }

    private void initRv() {
        detailsAdapter = new DetailsAdapter();
        rvDetailsData.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDetailsData.setAdapter(detailsAdapter);
    }


    @Override
    public void resetPhoneDetails() {
        LogUtil.d(presenter.getData().getBase().getBaseName());
        // 判定图集是否大于1
        if (presenter.getData().getPhoto().size() > 1) {
            // 显示轮播图
            drawBanner(presenter.getData().getPhoto());
        } else if (presenter.getData().getPhoto().size() == 1){
            // 显示单张图
            drawMaxImg(presenter.getData().getPhoto().get(0));
        } else {
            // 显示无详情图
        }
        // 显示基础数据 名称 加卖点
        DetailsAdapter.TitleNode titleNode = new DetailsAdapter.TitleNode(presenter.getData().getBase());
        detailsAdapter.addData(titleNode);
        // 屏幕数据 参数列表  二元列表
        DetailsAdapter.RootNode showNode = handleShow(presenter.getData().getShow());
        if (showNode != null) {
            detailsAdapter.addData(showNode);
        }
        // 外观信息
        DetailsAdapter.RootNode facade = handleFacade(presenter.getData().getFacade());
        if (facade != null) {
            detailsAdapter.addData(facade);
        }

        // 硬件配置信息
        DetailsAdapter.RootNode config = handleConfig(presenter.getData().getConfig());
        if (config != null) {
            detailsAdapter.addData(config);
        }

        // 续航能力 = 电池 + 充电


        // 相机参数

        // 外观信息

        // 用户评价

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    /**
     * 加载轮播图
     * @param photoList 轮播图数据
     */
    private void drawBanner(List<PhotoBean> photoList) {
        // 推荐数据没有 退出
        if (photoList == null || photoList.size() <= 1) {
            return;
        }
        Banner banner = new Banner(getApplicationContext());

        //标题 + 图片url
        List<String> titles;
        List<String> imgurls;

        // 设置轮播图的宽高
        banner.setLayoutParams(new ViewGroup.LayoutParams(
                context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels/2));
        // 设置标题
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setDelayTime(5000);
        // 如果api高，就用steam。不行就用老方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            titles = photoList.stream().map(e -> e.getPhoneLocation()).collect(Collectors.toList());
            banner.setBannerTitles(titles);

            imgurls = photoList.stream().map(e -> "http://" + e.getPhotoMin()).collect(Collectors.toList());
            banner.setImages(imgurls);
        } else {
            titles = new LinkedList<>();
            imgurls = new LinkedList<>();
            for (PhotoBean phonto : photoList) {
                titles.add(phonto.getPhoneLocation());
                imgurls.add("http://" + phonto.getPhotoMin());
            }
            banner.setBannerTitles(titles);
            banner.setImages(imgurls);
        }
        banner.setOnBannerListener(position ->
                ToastUtil.makeText("点击了 " + titles.get(position))
        );
        //设置轮播图加载方式
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
        detailsAdapter.addHeaderView(banner);
    }

    /**
     * 加载顶部是单张图片的情况
     * @param phone 单图
     */
    private void drawMaxImg(PhotoBean phone) {
        if (phone == null || phone.getPhotoMin() == null) {
            return;
        }
        ImageView img = new ImageView(getApplicationContext());
        // 设置图片宽高
        img.setLayoutParams(new ViewGroup.LayoutParams(
                context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels/2));
        // 加载图片
        Glide.with(getApplicationContext()).load("http://" + phone.getPhotoMin()).into(img);
        detailsAdapter.addHeaderView(img);
    }

    /**
     * 处理屏幕显示数据
     * @param show 显示数据集
     * @return 显示节点
     */
    private DetailsAdapter.RootNode handleShow(PhoneShow show) {

        List<BaseNode> childNodeList = new LinkedList<>();

        // 屏幕材质
        if (show.getShowTexture() != null && !show.getShowTexture().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("屏幕材质", show.getShowTexture());
            childNodeList.add(childNode);
        }
        // 屏幕尺寸
        if (show.getShowSize() != null && !show.getShowSize().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("屏幕尺寸", show.getShowSize());
            childNodeList.add(childNode);
        }
        // 主屏分辨率
        if (show.getShowResolratio() != null && !show.getShowResolratio().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("主屏分辨率", show.getShowResolratio());
            childNodeList.add(childNode);
        }
        // 屏幕像素排列方式
        if (show.getShowArrange() != null && !show.getShowArrange().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("屏幕像素排列方式", show.getShowArrange());
            childNodeList.add(childNode);
        }
        // 息屏显示
        if (show.getShowAod() != null && !show.getShowAod().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("息屏显示", show.getShowAod());
            childNodeList.add(childNode);
        }
        // 色彩管理
        if (show.getShowColorcontrol() != null && !show.getShowColorcontrol().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("色彩管理", show.getShowColorcontrol());
            childNodeList.add(childNode);
        }
        // 手动最大亮度
        if (show.getShowMaxluminance() != null && !show.getShowMaxluminance().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("手动最大亮度", show.getShowMaxluminance());
            childNodeList.add(childNode);
        }
        // 激发亮度
        if (show.getShowStimulateluminance() != null && !show.getShowStimulateluminance().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("激发亮度", show.getShowStimulateluminance());
            childNodeList.add(childNode);
        }
        // 默认色温
        if (show.getShowColortem() != null && !show.getShowColortem().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("默认色温", show.getShowColortem());
            childNodeList.add(childNode);
        }
        // SRGB覆盖
        if (show.getShowSrgb() != null && !show.getShowSrgb().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("SRGB覆盖", show.getShowSrgb());
            childNodeList.add(childNode);
        }
        // P3覆盖
        if (show.getShowP3() != null && !show.getShowP3().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("P3覆盖", show.getShowP3());
            childNodeList.add(childNode);
        }
        // 屏幕素质
        if (show.getShowQuality() != null && !show.getShowQuality().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("屏幕素质", show.getShowQuality());
            childNodeList.add(childNode);
        }
        // 屏幕其他
        if (show.getShowOther() != null && !show.getShowOther().isEmpty()) {
            DetailsAdapter.ChildNode childNode = new DetailsAdapter.ChildNode("屏幕其他", show.getShowOther());
            childNodeList.add(childNode);
        }

        // 如果没有数据就退出
        if (childNodeList.size() < 1) {
            return null;
        }
        DetailsAdapter.RootNode showNode = new DetailsAdapter.RootNode(childNodeList, "屏幕");
        return showNode;
    }

    /**
     * 处理外观信息参数
     * @param facade 外观数据
     * @return 外观的主节点
     */
    private DetailsAdapter.RootNode handleFacade(PhoneFacade facade) {
        List<BaseNode> childNodeList = new LinkedList<>();

        // 外观尺寸
        if (facade.getFacadeSize() != null && !facade.getFacadeSize().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("外观", facade.getFacadeSize()));
        }
        // 重量
        if (facade.getFacadeWeight() != null && !facade.getFacadeWeight().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("总量", facade.getFacadeWeight()));
        }
        // 材质
        if (facade.getFacadeTexture() != null && !facade.getFacadeTexture().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("材质", facade.getFacadeTexture()));
        }
        // 其他外观参数
        if (facade.getFacadeOther() != null && !facade.getFacadeOther().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("其他外观参数", facade.getFacadeOther()));
        }

        if (childNodeList.size() < 1) {
            return null;
        }
        DetailsAdapter.RootNode facadeNode = new DetailsAdapter.RootNode(childNodeList, "硬件配置");
        return facadeNode;
    }

    /**
     * 处理配置信息参数
     * @param config 配置数据
     * @return 配置的主节点
     */
    private DetailsAdapter.RootNode handleConfig(PhoneConfig config) {

        List<BaseNode> childNodeList = new LinkedList<>();

        // soc名称
        if (config.getSocName() != null && !config.getSocName().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("cpu型号", config.getSocName()));
        }
        // 存储组合
        if (config.getConfigMemory() != null && !config.getConfigMemory().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("存储组合", config.getConfigMemory()));
        }
        // 解锁方式
        if (config.getConfigUnlock() != null && !config.getConfigUnlock().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("解锁方式", config.getConfigUnlock()));
        }
        // 连接性
        if (config.getConfigNetworking() != null && !config.getConfigNetworking().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("连接方式", config.getConfigNetworking()));
        }

        // 如果没有数据就退出
        if (childNodeList.size() < 1) {
            return null;
        }
        DetailsAdapter.RootNode showNode = new DetailsAdapter.RootNode(childNodeList, "配置");
        return showNode;
    }
}
