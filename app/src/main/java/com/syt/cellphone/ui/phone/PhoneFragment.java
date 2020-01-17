package com.syt.cellphone.ui.phone;

import android.content.Intent;
import android.widget.SearchView;
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
import com.syt.cellphone.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author shenyutian
 */
public class PhoneFragment extends BaseFragment<PhonePresenter> implements PhoneView {

    @BindView(R.id.sv_phone_search)
    SearchView svPhoneSearch;
    @BindView(R.id.id_phone_title_name)
    TextView idPhoneTitleName;
    @BindView(R.id.vp2_phone_fragment)
    ViewPager2 vp2PhoneFragment;
    @BindView(R.id.tabLayout_phone_top)
    TabLayout tabLayoutPhoneTop;
    private List<Fragment> fragments = new ArrayList<>();
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

        // 加载设置搜索控件
        initSearch();
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
     * 加载配置搜索
     */
    private void initSearch() {
        svPhoneSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 搜索按钮监听
            @Override
            public boolean onQueryTextSubmit(String query) {
                LogUtil.d("搜索监听: " + query);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("content", query);
                startActivity(intent);
                return false;
            }
            // 搜索内容变化监听
            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtil.d("文本监听: " + newText);
                return false;
            }
        });
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
