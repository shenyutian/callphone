package com.syt.cellphone.ui.setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.ui.SytMainActivity;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingView {


    @BindView(R.id.tv_setting_theme)
    TextView tvSettingTheme;
    @BindView(R.id.iv_setting_night_switch)
    ImageView ivSettingNightSwitch;
    @BindView(R.id.constraintLayout_setting_person)
    ConstraintLayout constraintLayoutPerson;

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

    @OnClick({R.id.tv_setting_theme, R.id.iv_setting_night_switch, R.id.constraintLayout_setting_person})
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
            case R.id.constraintLayout_setting_person:
                // 暂时出现登录dialog
                View loginView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_login, null);
                final EditText etName = loginView.findViewById(R.id.et_user_login_name);
                final EditText etPass = loginView.findViewById(R.id.et_user_login_pass);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setView(loginView)
                        .setTitle("登录弹窗")
                        .setPositiveButton("登录", ((dialog, which) -> {

                            PhoneUser user = new PhoneUser();

                            user.setUserName(etName.getText().toString().trim());
                            user.setUserPass(etPass.getText().toString().trim());

                            if (user.getUserName() == null || user.getUserName().isEmpty()) {
                                Toast.makeText(context, "账号不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (user.getUserPass() == null || user.getUserPass().isEmpty()) {
                                Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
                            }

                            SharedConfigUtil.saveUserName(user.getUserName());

                            fpresenter.handleUserLogin(user);
                            dialog.dismiss();
                        }));
                builder.create().show();
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

    @Override
    public void refresh() {
        ToastUtil.makeText("登录成功!");
    }
}
