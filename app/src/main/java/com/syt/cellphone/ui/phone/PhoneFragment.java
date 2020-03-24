package com.syt.cellphone.ui.phone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.ui.phone.classifyPhone.ClassifyFragment;
import com.syt.cellphone.ui.phone.search.SearchActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author shenyutian
 */
public class PhoneFragment extends BaseFragment<PhonePresenter> implements PhoneView {

    @BindView(R.id.iv_phone_search)
    ImageView ivPhoneSearch;
    @BindView(R.id.id_phone_title_name)
    TextView idPhoneTitleName;
    @BindView(R.id.vp2_phone_fragment)
    ViewPager2 vp2PhoneFragment;
    @BindView(R.id.tabLayout_phone_top)
    TabLayout tabLayoutPhoneTop;
    private List<String> items = new ArrayList<>(8);
    private PhonePagerAdapter phonePagerAdapter;

    public static PhoneFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PhoneFragment fragment = new PhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected PhonePresenter initPresenter() {
        return new PhonePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_phone;
    }

    @Override
    protected void initData() {
        phonePagerAdapter = new PhonePagerAdapter(this);
        vp2PhoneFragment.setAdapter(phonePagerAdapter);

        items.add("首页");
        items.add("最近更新");
        items.add("小米");
        items.add("华为");
        items.add("oppo");
        items.add("vivo");
        items.add("三星");
        items.add("苹果");

        // 设置菜单列表
//        for (String item : items) {
//            tabLayoutPhoneTop.addTab(tabLayoutPhoneTop.newTab().setText(item));
//        }

        // 连接tabLayout和viewPage2
        new TabLayoutMediator(tabLayoutPhoneTop, vp2PhoneFragment, (@NonNull TabLayout.Tab tab, int position) -> {
            tab.setText(items.get(position));
//            tab.setIcon(R.mipmap.ic_setting_open);
//            tab.setTag("hello");
        }).attach();
        //最大保存10个fragment界面
        vp2PhoneFragment.setOffscreenPageLimit(10);
        // 自定义修改下划线
//        changeTextColor();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }

    private void changeTextColor() {
        tabLayoutPhoneTop.post(() -> {
            try {
                // 获取 下划线颜色 这个成员对象的名称 Field代表类的成员变量
                Field field = TabLayout.class.getDeclaredField("mTabStrip");
                // true的值表示反射对象应该在使用时抑制Java语言访问检查。 false的值表示反映的对象应该强制执行Java语言访问检查
                field.setAccessible(true);
                // 拿mtabStrip属性的值 将类传入获取对象值
                Object mTabStrip = field.get(tabLayoutPhoneTop);
                // 通过mTabStrip对象来获取 方法，不能用field来获取class类，参数不能写Integer.class   Method代表类的方法。
                Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
                method.setAccessible(true);
                // 底层方法 mTabStrip对象 中 执行method方法 传入颜色参数
                method.invoke(mTabStrip, Color.parseColor("#444444"));
            } catch (Exception e) {
                e.printStackTrace();
                Logger.d("tabLayout反射异常: " + e.getMessage());
            }
        });
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

    @OnClick({R.id.iv_phone_search, R.id.id_phone_title_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_phone_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.id_phone_title_name:
                break;
            default:
                break;
        }
    }

    /**
     * @author shenyutian
     * @data 2019-12-24 15:35
     * 功能 tablayout 下面的fragment 适配器
     */
    class PhonePagerAdapter extends FragmentStateAdapter {

        public PhonePagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return ClassifyFragment.newInstance(items.get(position), position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
