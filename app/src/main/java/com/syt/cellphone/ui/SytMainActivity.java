package com.syt.cellphone.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.luck.picture.lib.tools.SPUtils;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.base.MyApp;
import com.syt.cellphone.ui.brank.BrandFragment;
import com.syt.cellphone.ui.phone.PhoneFragment;
import com.syt.cellphone.ui.setting.SettingFragment;
import com.syt.cellphone.ui.soc.SocFragment;
import com.syt.cellphone.ui.started.MyStartedActivity;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 最开始的activity,主页就是它
 * @author syt
 */
public class SytMainActivity extends BaseActivity<SytMainPresenter> implements SytMainView, View.OnLongClickListener {

    @BindView(R.id.frameLayout_main_content)
    FrameLayout frameLayoutMainContent;
    @BindView(R.id.tv_one_bottom_phone)
    TextView tvOneBottomPhone;
    @BindView(R.id.iv_one_bottom_phone)
    ImageView ivOneBottomPhone;
    @BindView(R.id.constraintLayout_one_bottom_phone)
    ConstraintLayout constraintLayoutOneBottomPhone;
    @BindView(R.id.tv_two_bottom_brand)
    TextView tvTwoBottomBrand;
    @BindView(R.id.iv_two_bottom_brand)
    ImageView ivTwoBottomBrand;
    @BindView(R.id.constraintLayout_two_bottom_brand)
    ConstraintLayout constraintLayoutTwoBottomBrand;
    @BindView(R.id.tv_three_bottom_soc)
    TextView tvThreeBottomSoc;
    @BindView(R.id.iv_three_bottom_soc)
    ImageView ivThreeBottomSoc;
    @BindView(R.id.constraintLayout_three_bottom_soc)
    ConstraintLayout constraintLayoutThreeBottomSoc;
    @BindView(R.id.tv_four_bottom_setting)
    TextView tvFourBottomSetting;
    @BindView(R.id.iv_four_bottom_setting)
    ImageView ivFourBottomSetting;
    @BindView(R.id.constraintLayout_four_bottom_setting)
    ConstraintLayout constraintLayoutFourBottomSetting;
    @BindView(R.id.conlay_main_top)
    ConstraintLayout conlayMainTop;
    @BindView(R.id.drawer_layout_main_activity)
    DrawerLayout drawerLayoutMainActivity;

    /**
     * currentFragment  当前碎片
     *
     * fragments        里面的内容
     * phoneFragment    1号碎片  手机
     * brandFragment    2号碎片  品牌
     * socFragment      3号碎片  soc
     * settingFragment  4号碎片  设置
     * CURRENT_FRAGMENT 当前显示的fragment 的索引
     * currentIndex     当前选择的碎片
     */
    private Fragment            currentFragment     = PhoneFragment.newInstance();
    private List<Fragment>      fragments           = new ArrayList<>();
    private static final String CURRENT_FRAGMENT    = "STATE_FRAGMENT_SHOW";
    private int                 currentIndex         = Config.getBottomMenu();

    /**
     * 退出应用时，记录上一次按返回按钮的时间
     */
    private long countDown;

    private static final String TAG = "MainActivity";

    @Override
    protected SytMainPresenter createPresenter() {
        return new SytMainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_syt_main;
    }

    @Override
    protected void initData() {

        // 权限申请
        initPermission();

        // 加载设置布局
        fragments.add(PhoneFragment.newInstance());
        fragments.add(BrandFragment.newInstance());
        fragments.add(SocFragment.newInstance());
        fragments.add(SettingFragment.newInstance());

        //设置底部长按监听
        constraintLayoutOneBottomPhone.setOnLongClickListener(this);
        constraintLayoutTwoBottomBrand.setOnLongClickListener(this);
        constraintLayoutThreeBottomSoc.setOnLongClickListener(this);
        constraintLayoutFourBottomSetting.setOnLongClickListener(this);

        //设置初始默认颜色
        setTabDefaultColor();
        //设置默认主题
        setTheme();
        //保存设备序列号
//        FileUtil.getStrial(getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判定是否第一次开启应用
        boolean isFirstOpen = SPUtils.getInstance().getBoolean("first_open", false);
        if (!isFirstOpen) {
            // 第一次启动 介绍应用
            Intent intent = new Intent(this, MyStartedActivity.class);
            startActivity(intent);
        } else {
            // todo 临时跳转管理员界面
//            Intent intent = new Intent(this, AdminActivity.class);
//            startActivity(intent);
            // 判断是否启动过应用 或者 广告按钮关了
            SPUtils.getInstance().put("first_open", true);
            if (!MyApp.isStart || SPUtils.getInstance().getBoolean("advertising", false)) {
                // 启动了   ->    无广告

            } else {
                // 无 -> 有广告
                MyApp.isStart = true;

            }

        }

        Log.e(TAG, "onCreate: " + currentFragment.getClass().getName());

        // 内存重启时调用 取出内存中保存的fragment 名称
        if (savedInstanceState != null) {

            // 查询内存中是否有没有被回收的fragment
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragmentList) {
                Logger.d(fragment);
                // 将当前的fragment 等于上一次的
                currentFragment = fragment;
                replaceFragment(fragment);
            }
//            this.fragments.set(1, getSupportFragmentManager().findFragmentByTag(BrandFragment.class.getName()));
//            this.fragments.set(2, getSupportFragmentManager().findFragmentByTag(SocFragment.class.getName()));
//            this.fragments.set(3, getSupportFragmentManager().findFragmentByTag(SettingFragment.class.getName()));



            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, currentIndex);
            Intent intent = new Intent();
            intent.putExtra("param", currentIndex);
            presenter.switchMenus(intent);
        } else {
            // 正常启动时调用
            // 选择底部菜单
            presenter.switchMenus(getIntent());
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 保存当前活动状态
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // 保存当前 fragment 索引
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: " + currentFragment);
        currentFragment.onDestroy();
        super.onDestroy();
    }

    @OnClick({R.id.constraintLayout_one_bottom_phone, R.id.constraintLayout_two_bottom_brand, R.id.constraintLayout_three_bottom_soc, R.id.constraintLayout_four_bottom_setting})
    public void onViewClicked(View view) {
        // 底部菜单还原
        setTabDefaultColor();
        // 显示指定碎片
        showFragment(view.getId());
    }

    /**
     * 长按监听
     * @param v 视图
     * @return 实现结果
     */
    @Override
    public boolean onLongClick(View v) {
        showFragment(v.getId());
        return false;
    }

    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.REQUEST_INSTALL_PACKAGES,
        };

        ArrayList<String> toApplyList = new ArrayList<>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
                LogUtil.d("有权限没有申请");
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    /**
     * 恢复默认菜单颜色
     */
    private void setTabDefaultColor() {
        tvOneBottomPhone.setTextColor(Color.GRAY);
        ivOneBottomPhone.setColorFilter(Color.GRAY);
        tvTwoBottomBrand.setTextColor(Color.GRAY);
        ivTwoBottomBrand.setColorFilter(Color.GRAY);
        tvThreeBottomSoc.setTextColor(Color.GRAY);
        ivThreeBottomSoc.setColorFilter(Color.GRAY);
        tvFourBottomSetting.setTextColor(Color.GRAY);
        ivFourBottomSetting.setColorFilter(Color.GRAY);
    }

    /**
     * 设置fragment
     */
    private void setFragment(Fragment fragment) {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        Log.e(TAG, "setFragment: " + fragmentList);

        // 判定是否被添加过了  setTransition 添加动画
        if (!fragments.get(currentIndex-1).isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .hide(currentFragment)
                    .add(R.id.frameLayout_main_content, fragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        }
        currentFragment = fragment;
    }

    /**
     * 设置主题
     */
    private void setTheme() {

        // 改成原生的深色模式
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

//        if (SharedConfigUtil.getNightOnOff()) {
            //夜间
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
            //日间
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
    }

    /**
     * 设置碎片选择
     * 1 手机
     * 2 品牌
     * 3 处理器
     * 4 设置
     */
    @Override
    public void showFragment(int menuNum) {

//        ToastUtil.makeText("menu: " + menuNum);
        Logger.d("menu: " + menuNum);
        switch (menuNum) {
            case 1:
            case R.id.constraintLayout_one_bottom_phone:
                tvOneBottomPhone.setTextColor(Color.RED);
                ivOneBottomPhone.setColorFilter(Color.RED);
                currentIndex = 1;
                setFragment(fragments.get(0));
                break;
            case 2:
            case R.id.constraintLayout_two_bottom_brand:
                tvTwoBottomBrand.setTextColor(Color.RED);
                ivTwoBottomBrand.setColorFilter(Color.RED);
                currentIndex = 2;
                setFragment(fragments.get(1));
                break;
            case 3:
            case R.id.constraintLayout_three_bottom_soc:
                tvThreeBottomSoc.setTextColor(Color.RED);
                ivThreeBottomSoc.setColorFilter(Color.RED);
                currentIndex = 3;
                setFragment(fragments.get(2));
                break;
            case 4:
            case R.id.constraintLayout_four_bottom_setting:
                tvFourBottomSetting.setTextColor(Color.RED);
                ivFourBottomSetting.setColorFilter(Color.RED);
                currentIndex = 4;
                setFragment(fragments.get(3));
                break;
            default:
                ToastUtil.makeText("点击了不存在的地方");
                break;
        }
    }

    /**
     * 替换fragments中的fragment为上一次保存的
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        Log.d(TAG, "replaceFragmentName: " + fragment.getClass().getName());
        switch (fragment.getClass().getName()) {
            case "com.syt.cellphone.ui.phone.PhoneFragment":
                fragments.set(0, fragment);
                break;
            case "com.syt.cellphone.ui.brank.BrandFragment":
                fragments.set(1, fragment);
                break;
            case "com.syt.cellphone.ui.soc.SocFragment":
                fragments.set(2, fragment);
                break;
            case "com.syt.cellphone.ui.setting.SettingFragment":
                fragments.set(3, fragment);
                break;
            default:
                break;
        }
    }

    /**
     * 监听按键键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 2次按返回的时间差
            int countDownTime = 2000;
            if (System.currentTimeMillis() - countDown > countDownTime) {
                Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_LONG).show();
                countDown = System.currentTimeMillis();
            } else {
                //退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
