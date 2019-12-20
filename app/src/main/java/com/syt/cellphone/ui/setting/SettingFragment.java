package com.syt.cellphone.ui.setting;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.ui.SytMainActivity;
import com.syt.cellphone.util.SharedConfigUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingView {


    @BindView(R.id.tv_setting_theme)
    TextView tvSettingTheme;
    @BindView(R.id.iv_setting_night_switch)
    ImageView ivSettingNightSwitch;

    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
        // 查询来选择是否夜间模式
        switchNightOnOff();
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

    @OnClick({R.id.tv_setting_theme, R.id.iv_setting_night_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setting_theme:
                break;
            case R.id.iv_setting_night_switch:
                if (!SharedConfigUtil.getNightOnOff()) {
                    //日间 切换 夜间
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedConfigUtil.saveNightOnOff(true);
                } else {
                    //夜间 切换 日间
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedConfigUtil.saveNightOnOff(false);
                }
                //重启activity, 设置第4个fragment
                Config.setBottomMenu(4);
                Intent intent = new Intent(context, SytMainActivity.class);
                intent.putExtra("param", 4);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 查询夜间模式开关，来确定视图
     */
    private void switchNightOnOff() {
        if (SharedConfigUtil.getNightOnOff()) {
            //夜间
            ivSettingNightSwitch.setImageResource(R.mipmap.ic_setting_open);
        } else {
            //日间
            ivSettingNightSwitch.setImageResource(R.mipmap.ic_setting_close);
        }
    }
}
