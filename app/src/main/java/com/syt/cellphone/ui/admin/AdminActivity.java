package com.syt.cellphone.ui.admin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author shenyutian
 * @data 2020/3/25 5:22 PM
 * 功能 管理员活动
 */
public class AdminActivity extends BaseActivity<AdminPresenter> implements AdminView {

    private static final String TAG = "AdminActivity";
    @BindView(R.id.vp2_admin_fragment)
    ViewPager2 vp2AdminFragment;
    @BindView(R.id.bottom_navigation_view_admin)
    BottomNavigationView bottomNavigationViewAdmin;
    @BindView(R.id.nav_view_admin_left)
    NavigationView navViewAdminLeft;
    @BindView(R.id.drawer_layout_admin)
    DrawerLayout drawerLayoutAdmin;

    private List<Fragment> fragments;

    @Override
    protected AdminPresenter createPresenter() {
        return new AdminPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin;
    }

    @Override
    protected void initData() {

        fragments = getFragments();

        vp2AdminFragment.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }
        });


        vp2AdminFragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            // 当前 vp2 的 item
            private int item;
            // 保存上一次滑动状态
            private int direction;

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                Log.d(TAG, "onPageSelected: position " + position);
                // todo 底部改变
//                bottomNavigationViewAdmin.setSelectedItemId(position);
                item = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
//                Log.d(TAG, "onPageScrollStateChanged: state = " + state);
                if (item == 0 && direction == 1 && state == 0) {
                    // 左滑到顶了
                    drawerLayoutAdmin.openDrawer(GravityCompat.START);
                }
                direction = state;
            }
        });

        bottomNavigationViewAdmin.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_admin_recommend:
                    vp2AdminFragment.setCurrentItem(0);
                    break;
                case R.id.tab_admin_phone:
                    vp2AdminFragment.setCurrentItem(1);
                    break;
                case R.id.tab_admin_soc:
                    vp2AdminFragment.setCurrentItem(2);
                    break;
                case R.id.tab_admin_user:
                    vp2AdminFragment.setCurrentItem(3);
                    break;
                default:
                    break;
            }
            return true;
        });

    }

    @Override
    public Context getContext() {
        return this;
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>(4);

        Bundle bundle = new Bundle();
        bundle.putString("title", String.valueOf(R.string.admin_recommend));
        RecommendAdminFragment recommendAdminFragment = new RecommendAdminFragment();
        recommendAdminFragment.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle.putString("title", String.valueOf(R.string.admin_recommend));
        RecommendAdminFragment recommendAdminFragment2 = new RecommendAdminFragment();
        recommendAdminFragment.setArguments(bundle);

        Bundle bundle3 = new Bundle();
        bundle.putString("title", String.valueOf(R.string.admin_recommend));
        RecommendAdminFragment recommendAdminFragment3 = new RecommendAdminFragment();
        recommendAdminFragment.setArguments(bundle);

        Bundle bundle4 = new Bundle();
        bundle.putString("title", String.valueOf(R.string.admin_recommend));
        RecommendAdminFragment recommendAdminFragment4 = new RecommendAdminFragment();
        recommendAdminFragment.setArguments(bundle);

        fragments.add(recommendAdminFragment);
        fragments.add(recommendAdminFragment2);
        fragments.add(recommendAdminFragment3);
        fragments.add(recommendAdminFragment4);

        return fragments;
    }

    /**
     * 权限检查
     *
     * @param neededPermissions 需要的权限
     * @return 是否全部被允许
     */
    protected boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }

}
