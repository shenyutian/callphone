package com.syt.cellphone.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.ui.brank.BrandFragment;
import com.syt.cellphone.ui.phone.PhoneFragment;
import com.syt.cellphone.ui.setting.SettingFragment;
import com.syt.cellphone.ui.soc.SocFragment;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


public class SytMainActivity extends BaseActivity<SytMainPresenter> implements SytMainView, View.OnLongClickListener, TextToSpeech.OnInitListener {

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
    private Fragment currentFragment = new PhoneFragment();
    private List<Fragment> fragments = new ArrayList<>();
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private int currentIndex = Config.getBottomMenu();

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
        fragments.add(new PhoneFragment());
        fragments.add(new BrandFragment());
        fragments.add(new SocFragment());
        fragments.add(new SettingFragment());
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
        // 内存重启时调用 取出内存中保存的fragment
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, currentIndex);
            Intent intent = new Intent();
            intent.putExtra("param", currentIndex);
            presenter.switchMenus(intent);
        } else {
            // 正常启动时调用
            // 选择底部菜单
            presenter.switchMenus(getIntent());
        }
        initTextToSpeech();
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

        // 判定是否被添加过了
        if (!fragments.get(currentIndex-1).isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(currentFragment)
                    .add(R.id.frameLayout_main_content, fragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
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
     * 1 手机
     * 2 品牌
     * 3 处理器
     * 4 设置
     */
    @Override
    public void showFragment(int menuNum) {
        ToastUtil.makeText("menu: " + menuNum);
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
                setFragment(fragments.get(1));
                currentIndex = 2;
                // TODO validate success, do something
                if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            /*
                TextToSpeech的speak方法有两个重载。
                // 执行朗读的方法
                speak(CharSequence text,int queueMode,Bundle params,String utteranceId);
                // 将朗读的的声音记录成音频文件
                synthesizeToFile(CharSequence text,Bundle params,File file,String utteranceId);
                第二个参数queueMode用于指定发音队列模式，两种模式选择
                （1）TextToSpeech.QUEUE_FLUSH：该模式下在有新任务时候会清除当前语音任务，执行新的语音任务
                （2）TextToSpeech.QUEUE_ADD：该模式下会把新的语音任务放到语音任务之后，
                等前面的语音任务执行完了才会执行新的语音任务
             */
                    textToSpeech.speak("语音播放测试", TextToSpeech.QUEUE_FLUSH, null);
                }
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

    private TextToSpeech textToSpeech;


    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(this, this);
        textToSpeech.setPitch(1.0f);
        textToSpeech.setSpeechRate(0.5f);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            /*
                使用的是小米手机进行测试，打开设置，在系统和设备列表项中找到更多设置，
            点击进入更多设置，在点击进入语言和输入法，见语言项列表，点击文字转语音（TTS）输出，
            首选引擎项有三项为Pico TTs，科大讯飞语音引擎3.0，度秘语音引擎3.0。其中Pico TTS不支持
            中文语言状态。其他两项支持中文。选择科大讯飞语音引擎3.0。进行测试。

                如果自己的测试机里面没有可以读取中文的引擎，
            那么不要紧，我在该Module包中放了一个科大讯飞语音引擎3.0.apk，将该引擎进行安装后，进入到
            系统设置中，找到文字转语音（TTS）输出，将引擎修改为科大讯飞语音引擎3.0即可。重新启动测试
            Demo即可体验到文字转中文语言。
             */
            // setLanguage设置语言
            int result = textToSpeech.setLanguage(Locale.CHINA);
            // TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失
            // TextToSpeech.LANG_NOT_SUPPORTED：不支持
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
