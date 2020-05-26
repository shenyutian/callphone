package com.syt.cellphone.ui.started;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.model.SliderPage;
import com.luck.picture.lib.tools.SPUtils;
import com.syt.cellphone.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenyutian
 * @data 2020/5/8 11:10 AM
 * 功能 启动页
 */
public class MyStartedActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        // 保存第一次启动的状态
        SPUtils.getInstance().put("first_open", true);
    }

    private void initView() {
        initSlider();

        setVibrateDuration(30);
        setDoneText("完成");
        setSkipText("跳过");
    }

    private void initSlider() {

        List<SliderPage> sliderPages = new ArrayList<>(4);
        SliderPage sliderPage = null;

        sliderPage = new SliderPage();
        sliderPage.setTitle(getString(R.string.beautiful_home_page));
        // 设置精美首页
        sliderPage.setBackgroundDrawable(R.drawable.start_one);
        sliderPages.add(sliderPage);

        sliderPage = new SliderPage();
        sliderPage.setTitle(getString(R.string.the_original_night));
        // 设置黑夜模式
        sliderPage.setBackgroundDrawable(R.drawable.start_two);
        sliderPages.add(sliderPage);

        sliderPage = new SliderPage();
        sliderPage.setTitle(getString(R.string.details_are_introduced));
        // 设置详情介绍
        sliderPage.setBackgroundDrawable(R.drawable.start_three);
        sliderPages.add(sliderPage);

        sliderPage = new SliderPage();
        sliderPage.setTitle(getString(R.string.happy_login));
        // 设置愉快登录
        sliderPage.setBackgroundDrawable(R.drawable.start_four);
        sliderPages.add(sliderPage);


        for (int i = 0; i < sliderPages.size(); i++) {
            addSlide(AppIntroFragment.newInstance(sliderPages.get(i)));
        }
    }

    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
