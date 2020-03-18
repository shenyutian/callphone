package com.syt.cellphone.ui.phone;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.ui.phone.classifyPhone.ClassifyFragment;
import com.syt.cellphone.ui.phone.search.SearchActivity;

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
    private List<String> items = new ArrayList<>(6);
    private PhonePagerAdapter phonePagerAdapter;

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
        }).attach();
        //最大保存10个fragment界面
        vp2PhoneFragment.setOffscreenPageLimit(10);
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
