package com.syt.cellphone.ui.phone.details;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.R;
import com.syt.cellphone.adapter.DetailsAdapter;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.pojo.Estimate;
import com.syt.cellphone.pojo.PhoneBattery;
import com.syt.cellphone.pojo.PhoneCamera;
import com.syt.cellphone.pojo.PhoneConfig;
import com.syt.cellphone.pojo.PhoneFacade;
import com.syt.cellphone.pojo.PhoneShow;
import com.syt.cellphone.pojo.PhotoBean;
import com.syt.cellphone.dialog.InputTextMsgDialog;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.ToastUtil;
import com.syt.cellphone.widget.SytToolBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import butterknife.BindView;
import jp.wasabeef.blurry.Blurry;

/**
 * 手机详情
 * @author syt
 */
public class PhoneDetailsActivity extends BaseActivity<PhoneDetailsPresenter> implements PhoneDetailsView {

    /**
     * ------------  注入的布局  -------------
     * clPhoneDetails  底部的提示栏
     */
    @BindView(R.id.rv_details_data)
    RecyclerView rvDetailsData;
    @BindView(R.id.bar_details_top)
    SytToolBar barDetailsTop;
    @BindView(R.id.cl_phone_details_bottom)
    ConstraintLayout clPhoneDetails;

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
        Logger.d("\t设备名 " + android.os.Build.DEVICE + "\t设备型号 " + Build.MODEL + "\t设备厂家 " + Build.BRAND);
        // 沉浸式布局 todo 对异形屏有影响，暂时关闭
//        ImmersionBar.with(this).init();
        // 列表
        initRv();
        // 请求数据
        int phoneId = getIntent().getIntExtra("phoneId", 0);
        presenter.handlePhoneDetails(phoneId);

        // 弹窗 = 填写评价框
//            dialogEstimate();
        InputTextMsgDialog inputTextMsgDialog = new InputTextMsgDialog(context, R.style.dialog_center);
        inputTextMsgDialog.setmOnTextSendListener(msg -> {
            // 点击发送按钮后，回调这个方法，msg为输入的值
            ToastUtil.makeText(msg);
            // todo 获取设备型号 需要进行撞 数据库验证手机型号 执行提交操作

            Estimate estimate = new Estimate();
            estimate.setPhoneId(phoneId);
            estimate.setEstimateComment(msg);

            estimate.setEstimateTime(System.currentTimeMillis());
            presenter.handUnloadEstimate(estimate);
        });

        // 设置下面提示视图的点击事件
        clPhoneDetails.setOnClickListener( v -> {
            // 弹窗 = 填写评价框
//            dialogEstimate();
            inputTextMsgDialog.show();
            // 模糊当前的所有视图
//            Blurry.with(context).radius(10).sampling(2).onto((ViewGroup) getWindow().getDecorView());
        });
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
            drawNoImg();
        }
        // 显示基础数据 名称 加卖点 todo 没有数据的时候，需要处理
        DetailsAdapter.TitleNode titleNode = new DetailsAdapter.TitleNode(presenter.getData().getBase());
        detailsAdapter.addData(titleNode);

        // 配置信息
        DetailsAdapter.RootNode config = handleConfig(presenter.getData().getConfig());
        if (config != null) {
            detailsAdapter.addData(config);
        }

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

        // 相机参数
        DetailsAdapter.RootNode camera1 = handleCamera(presenter.getData().getCamera());
        if (camera1 != null) {
            detailsAdapter.addData(camera1);
        }

        // 续航能力 = 电池 + 充电
        DetailsAdapter.RootNode battery = handleBattery(presenter.getData().getBattery());
        if (battery != null) {
            detailsAdapter.addData(battery);
        }

        // 相机参数
        DetailsAdapter.RootNode camera = handleCamera(presenter.getData().getCamera());
        if (camera != null) {
            detailsAdapter.addData(camera);
        }

        // 用户评价列表
        handleEstimate(presenter.getData().getEstimate());

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
        banner.setOnBannerListener(position -> {
                    ToastUtil.makeText("点击了 " + titles.get(position));
                    imgMaxDialog(photoList.get(position).getPhotoMax());
                    // 模糊当前的所有视图
//                    Blurry.with(context).radius(10).sampling(2).onto((ViewGroup) getWindow().getDecorView());
                }
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
        // 点击查看大图
        img.setOnClickListener( v ->
            imgMaxDialog(phone.getPhotoMax())
        );
    }

    /**
     * 无详情图的情况，绘制一个无图。
     */
    private void drawNoImg() {
        ImageView img = new ImageView(getApplicationContext());
        // 设置图片宽高
        img.setLayoutParams(new ViewGroup.LayoutParams(
                context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels/2));
        // 加载图片
        Glide.with(getApplicationContext()).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=30305038,711507037&fm=26&gp=0.jpg").into(img);
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
        DetailsAdapter.RootNode facadeNode = new DetailsAdapter.RootNode(childNodeList, "外观信息");
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
        DetailsAdapter.RootNode showNode = new DetailsAdapter.RootNode(childNodeList, "硬件配置");
        return showNode;
    }

    /**
     * 处理电池充电配置信息参数
     * @param battery 电池充电数据
     * @return 电池充电的主节点
     */
    private DetailsAdapter.RootNode handleBattery(PhoneBattery battery) {

        List<BaseNode> childNOdeList = new LinkedList<>();

        // 电池容量
        if (battery.getBatteryCapacity() != null && !battery.getBatteryCapacity().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("电池容量", battery.getBatteryCapacity()));
        }

        // 快充协议
        if (battery.getBatteryScheme() != null && !battery.getBatteryScheme().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("快充协议", battery.getBatteryScheme()));
        }

        // 原装充电器
        if (battery.getBatteryCablecharger() != null && !battery.getBatteryCablecharger().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("原装充电器", battery.getBatteryCablecharger()));
        }

        // 无线充电
        if (battery.getBatteryWirelesscharger() != null && !battery.getBatteryWirelesscharger().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("无线充电", battery.getBatteryWirelesscharger()));
        }

        // 有线充满时间
        if (battery.getBatteryCabletime() != null && !battery.getBatteryCabletime().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("有线充满时间", battery.getBatteryCabletime()));
        }

        // 无线充满时间
        if (battery.getBatteryWirelesstime() != null && !battery.getBatteryWirelesstime().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("无线充满时间", battery.getBatteryWirelesstime()));
        }

        // 续航测试
        if (battery.getBatteryEndurance() != null && !battery.getBatteryEndurance().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("续航能力", battery.getBatteryEndurance()));
        }

        // 其他特性
        if (battery.getBatteryOther() != null && !battery.getBatteryOther().isEmpty()) {
            childNOdeList.add(new DetailsAdapter.ChildNode("其它特性", battery.getBatteryOther()));
        }

        DetailsAdapter.RootNode batteryNode = new DetailsAdapter.RootNode(childNOdeList, "电池充电参数");
        return batteryNode;
    }

    /**
     * 处理相机配置参数
     * @param camera 相机参数
     * @return 相机参数的主节点
     */
    private DetailsAdapter.RootNode handleCamera(PhoneCamera camera) {
        List<BaseNode> childNodeList = new LinkedList<>();

        // 相机数量
        if (camera.getCameraAmount() != null && !camera.getCameraAmount().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("相机数量", camera.getCameraAmount()));
        }

        // 感光元器件
        if (camera.getCameraCmos() != null && !camera.getCameraCmos().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("感光元件", camera.getCameraCmos()));
        }

        // 像素
        if (camera.getCameraPixel() != null && !camera.getCameraPixel().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("像素", camera.getCameraCmos()));
        }

        // 等效焦段
        if (camera.getCameraFocallength() != null && !camera.getCameraFocallength().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("等效焦段", camera.getCameraFocallength()));
        }

        // 光圈系数
        if (camera.getCameraAperture() != null && !camera.getCameraAperture().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("光圈系数", camera.getCameraAperture()));
        }

        // 光学防抖
        if (camera.getCameraOis() != null && !camera.getCameraOis().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("光学防抖", camera.getCameraOis()));
        }

        // 对焦方式
        if (camera.getCameraFosusing() != null && !camera.getCameraFosusing().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("对焦方式", camera.getCameraFosusing()));
        }

        // 拍照功能
        if (camera.getCameraGraph() != null && !camera.getCameraGraph().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("对焦方式", camera.getCameraGraph()));
        }

        // 视频规格
        if (camera.getCameraVideo() != null && !camera.getCameraVideo().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("视频规格", camera.getCameraVideo()));
        }

        // 视频防抖
        if (camera.getCameraSteadyvideo() != null && !camera.getCameraSteadyvideo().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("视频防抖", camera.getCameraSteadyvideo()));
        }

        // 慢动作视频
        if (camera.getCameraSlowmotion() != null && !camera.getCameraSlowmotion().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("慢动作视频", camera.getCameraSteadyvideo()));
        }

        // 备注
        if (camera.getCameraRemarks() != null && !camera.getCameraRemarks().isEmpty()) {
            childNodeList.add(new DetailsAdapter.ChildNode("备注", camera.getCameraRemarks()));
        }

        // 如果没有数据就退出
        if (childNodeList.size() < 1) {
            return null;
        }
        DetailsAdapter.RootNode showNode = new DetailsAdapter.RootNode(childNodeList, "相机参数");
        return showNode;
    }

    /**
     * 处理评论列表
     * @param estimates 评论数据
     */
    private void handleEstimate(List<Estimate> estimates) {
        DetailsAdapter.EstimateNode estimateNode;
        for (Estimate estimate : estimates) {
            estimateNode = new DetailsAdapter.EstimateNode(estimate);
            detailsAdapter.addData(estimateNode);
        }
    }

    /**
     * 点击大图 -> 大图弹窗
     * @param imgMax
     */
    private void imgMaxDialog(final String imgMax) {
        // 弹窗创建
        final AlertDialog toastDialog = new AlertDialog.Builder(context, R.style.DialogStyle).create();
        // 弹窗显示
        toastDialog.show();
        // 获取当前窗口
        Window window = toastDialog.getWindow();
        // 居中
        Objects.requireNonNull(window).setGravity(Gravity.CENTER);
        // 布局显示
        WindowManager.LayoutParams lp = window.getAttributes();
        // 宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setContentView(R.layout.dialog_max_img);

        // 绘制大图
        ImageView maxImg = window.findViewById(R.id.iv_details_max_img);
        Glide.with(this).load("http://" + imgMax).into(maxImg);
        LogUtil.d("图片地址  http://" + imgMax);

        // 点击消失事件 getWindow().getDecorView()获得顶级视图
        maxImg.setOnClickListener( v -> {
            LogUtil.d("图片点击事件触发");
            toastDialog.dismiss();
        });

    }



    private void dialogEstimate() {
        // 弹窗创建
        final AlertDialog toastDialog = new AlertDialog.Builder(context, R.style.DialogStyle).create();
        // 弹窗显示
        toastDialog.show();
        // 获取当前窗口
        Window window = toastDialog.getWindow();
        // 居中
        Objects.requireNonNull(window).setGravity(Gravity.CENTER);
        // 布局显示
        WindowManager.LayoutParams lp = window.getAttributes();
        // 宽高
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setContentView(R.layout.dialog_estimate);


        // 清除模糊
        toastDialog.setOnCancelListener((DialogInterface p) -> {
            Blurry.delete((ViewGroup) getWindow().getDecorView());
        });
    }
}
