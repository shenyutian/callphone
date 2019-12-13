package com.syt.cellphone.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.ui.brank.BrandFragment;
import com.syt.cellphone.ui.phone.PhoneFragment;
import com.syt.cellphone.ui.setting.SettingFragment;
import com.syt.cellphone.ui.soc.SocFragment;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class SytMainActivity extends BaseActivity<SytMainPresenter> implements SytMainView, View.OnLongClickListener {

    /**
     * phoneFragment    1号碎片  手机
     * brandFragment    2号碎片  品牌
     * socFragment      3号碎片  soc
     * settingFragment  4号碎片  设置
     */
    private static Fragment phoneFragment;
    private static Fragment brandFragment;
    private static Fragment socFragment;
    private static Fragment settingFragment;

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
        // 加载设置布局
        phoneFragment = new PhoneFragment();
        brandFragment = new BrandFragment();
        socFragment = new SocFragment();
        settingFragment = new SettingFragment();

        //设置底部长按监听
        constraintLayoutOneBottomPhone.setOnLongClickListener(this);
        constraintLayoutTwoBottomBrand.setOnLongClickListener(this);
        constraintLayoutThreeBottomSoc.setOnLongClickListener(this);
        constraintLayoutFourBottomSetting.setOnLongClickListener(this);

        //设置初始默认颜色
        setTabDefaultColor();
        //设置默认主题
        setTheme();

        //选择底部菜单
        presenter.switchMenus(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Context getContext() {
        return this;
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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_main_content, fragment)
                .commit();
    }

    /**
     * 设置主题
     */
    private void setTheme() {
        if (SharedConfigUtil.getNightOnOff()) {
            //夜间
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //日间
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * 设置碎片选择
     */
    @Override
    public void showFragment(int menuNum) {
        ToastUtil.makeText("menu: " + menuNum);
        switch (menuNum) {
            case 1:
            case R.id.constraintLayout_one_bottom_phone:
                tvOneBottomPhone.setTextColor(Color.RED);
                ivOneBottomPhone.setColorFilter(Color.RED);
                setFragment(phoneFragment);
                break;
            case 2:
            case R.id.constraintLayout_two_bottom_brand:
                tvTwoBottomBrand.setTextColor(Color.RED);
                ivTwoBottomBrand.setColorFilter(Color.RED);
                setFragment(brandFragment);
                break;
            case 3:
            case R.id.constraintLayout_three_bottom_soc:
                tvThreeBottomSoc.setTextColor(Color.RED);
                ivThreeBottomSoc.setColorFilter(Color.RED);
                setFragment(socFragment);
                break;
            case 4:
            case R.id.constraintLayout_four_bottom_setting:
                tvFourBottomSetting.setTextColor(Color.RED);
                ivFourBottomSetting.setColorFilter(Color.RED);
                setFragment(settingFragment);
                break;
            default:
                ToastUtil.makeText("点击了不存在的地方");
                break;
        }
    }


}
