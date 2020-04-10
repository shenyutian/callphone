package com.syt.cellphone.ui.admin;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.RuntimeABI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.base.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        // 激活人脸引擎
        activeEngine();

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

//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                Log.d(TAG, "onPageScrolled: position = " + position);
//                Log.d(TAG, "onPageScrolled: positionOffset = " + positionOffset);
//                Log.d(TAG, "onPageScrolled: positionOffsetPixels = " + positionOffsetPixels);
//            }

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
     * 在线激活所需的权限
     */
    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };

    /**
     * 激活引擎
     */
    private void activeEngine() {
        if (!checkSoFile()) {
            Log.e(TAG, "activeEngine: " + R.string.library_not_found);
            return;
        }
        if (!checkPermissions(NEEDED_PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, NEEDED_PERMISSIONS, 0x001);
            return;
        }
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                RuntimeABI runtimeABI = FaceEngine.getRuntimeABI();
                Log.i(TAG, "subscribe: getRuntimeABI() " + runtimeABI);

                long start = System.currentTimeMillis();
                int activeCode = FaceEngine.activeOnline(getApplicationContext(), Config.APP_ID, Config.SDK_KEY);
                Log.i(TAG, "subscribe cost: " + (System.currentTimeMillis() - start));
                emitter.onNext(activeCode);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer activeCode) {
                        if (activeCode == ErrorInfo.MOK) {
                            showMsg(getString(R.string.active_success));
                        } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {
                            showMsg(getString(R.string.already_activated));
                        } else {
                            showMsg(getString(R.string.active_failed, activeCode));
                        }
                        // 激活文件信息
                        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
                        int res = FaceEngine.getActiveFileInfo(getApplicationContext(), activeFileInfo);
                        if (res == ErrorInfo.MOK) {
                            Log.i(TAG, activeFileInfo.toString());
                            Logger.e("激活时间剩余：" + activeFileInfo.getEndTime());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 检查能否找到动态链接库，如果找不到，请修改工程配置
     *
     * @return 动态库是否存在
     */
    private boolean checkSoFile() {
        String[] libraries = new String[]{
                // 人脸相关
                "libarcsoft_face_engine.so",
                "libarcsoft_face.so",
                // 图像库相关
                "libarcsoft_image_util.so",
        };

        File dir = new File(getApplicationInfo().nativeLibraryDir);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        List<String> libraryNameList = new ArrayList<>();
        for (File file : files) {
            libraryNameList.add(file.getName());
        }
        boolean exists = true;
        for (String library : libraries) {
            exists &= libraryNameList.contains(library);
        }
        return exists;
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
