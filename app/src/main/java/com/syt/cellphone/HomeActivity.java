package com.syt.cellphone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.syt.cellphone.phone.PhoneFragment;
import com.syt.cellphone.recommend.RecommendFragment;
import com.syt.cellphone.soc.SocFragment;
import com.syt.cellphone.update.UpdateFragment;
import com.syt.cellphone.util.ActivityCollector;
import com.syt.cellphone.util.FragmentAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * 控件声明
     */
    private ImageButton imgBtnTableUpdate;
    private LinearLayout layUpdate;
    private ImageButton imgBtnTablePhone;
    private LinearLayout layPhone;
    private ImageButton imgBtnTableSoc;
    private LinearLayout laySoc;
    private ImageButton imgBtnTableRecommend;
    private LinearLayout layRecommend;
    private LinearLayout llayMenu;
    // 视图Pager 滑动
    private ViewPager mViewPager;
    // 碎片集合
    List<Fragment> fList = new ArrayList<>();
    // 碎片适配器，用于滑动用
    private FragmentAdapter mFragmentAdapter;

    /**
     * 四个fragment 底部四大按钮对应的界面
     */
    private UpdateFragment updateFragment; //最近更新
    private PhoneFragment phoneFragment; //手机
    private SocFragment socFragment; //soc 处理器视图
    private RecommendFragment recommendFragment; // 推荐视图
    private DrawerLayout drawer=null; // 滑动菜单

    /**
     * 建立视图
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActivityCollector.addActivity(this);
        initRegister();
        initView();
        initEvent();
    }

    void initEvent() {
        imgBtnTableUpdate.setOnClickListener(this);
        imgBtnTablePhone.setOnClickListener(this);
        imgBtnTableSoc.setOnClickListener(this);
        imgBtnTableRecommend.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {//当viewpager滑动时，对应的底部导航按钮的图片要变化
                int currentItem = mViewPager.getCurrentItem();
                setTabDefaultColor();
                switch (currentItem) {
                    case 0:
                        imgBtnTableUpdate.setImageResource(R.drawable.tab_update_pressed);
                        break;
                    case 1:
                        imgBtnTablePhone.setImageResource(R.drawable.tab_phone_pressed);
                        break;
                    case 2:
                        imgBtnTableSoc.setImageResource(R.drawable.tab_soc_pressed);
                        break;
                    case 3:
                        imgBtnTableRecommend.setImageResource(R.drawable.tab_recommend_pressed);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }

    /**
     *  注册控件
     */
    private void initRegister() {
        updateFragment = new UpdateFragment(); //最近更新
        phoneFragment = new PhoneFragment(); //手机
        socFragment = new SocFragment(); //soc 处理器视图
        recommendFragment = new RecommendFragment(); // 推荐视图

        imgBtnTableUpdate = findViewById(R.id.imgBtn_table_update);//更新按钮
        imgBtnTablePhone = findViewById(R.id.imgBtn_table_phone); // phone 按钮
        imgBtnTableSoc = findViewById(R.id.imgBtn_table_soc); // soc按钮
        imgBtnTableRecommend = findViewById(R.id.imgBtn_table_recommend); // 推荐按钮

        mViewPager = findViewById(R.id.id_viewpager);

        // 视图们
        layUpdate = findViewById(R.id.lay_update);
        layPhone = findViewById(R.id.lay_phone);
        laySoc = findViewById(R.id.lay_soc);
        layRecommend = findViewById(R.id.lay_recommend);
    }

    // 加入视图
    private void initView() {
        // 四个碎片fragment进入
        fList.add(updateFragment);
        fList.add(phoneFragment);
        fList.add(socFragment);
        fList.add(recommendFragment);
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), fList);
        //为ViewPager设置adapter
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
    }

    /**
     *     恢复底部菜单图片
     */
    private void setTabDefaultColor() {
        imgBtnTableUpdate.setImageResource(R.drawable.tab_update_normal);
        imgBtnTablePhone.setImageResource(R.drawable.tab_phone_normal);
        imgBtnTableSoc.setImageResource(R.drawable.tab_soc_normal);
        imgBtnTableRecommend.setImageResource(R.drawable.tab_recommend_normal);
    }

    /**
     * 监听事件，按钮以及滑动
     * @param v
     */
    @Override
    public void onClick(View v) {
        setTabDefaultColor();
        switch (v.getId()) {
            case R.id.lay_update:
                Toast.makeText(v.getContext(), "点击了底部", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.imgBtn_table_update:
                imgBtnTableUpdate.setImageResource(R.drawable.tab_update_pressed);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.lay_phone:
                break;
            case  R.id.imgBtn_table_phone:
                imgBtnTablePhone.setImageResource(R.drawable.tab_phone_pressed);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.lay_soc:
                Toast.makeText(v.getContext(), "点击了底部", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.imgBtn_table_soc:
                imgBtnTableSoc.setImageResource(R.drawable.tab_soc_pressed);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.lay_recommend:
            case  R.id.imgBtn_table_recommend:
                imgBtnTableRecommend.setImageResource(R.drawable.tab_recommend_pressed);
                mViewPager.setCurrentItem(3);
                break;
        }
    }
}
