package com.syt.cellphone.ui.setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.ui.SytMainActivity;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.widget.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingView {


    @BindView(R.id.tv_setting_theme)
    TextView tvSettingTheme;
    @BindView(R.id.iv_setting_night_switch)
    ImageView ivSettingNightSwitch;
    @BindView(R.id.tv_setting_click_login)
    TextView tvSettingClickLogin;
    @BindView(R.id.iv_setting_user_portrait)
    ImageView ivSettingUserPortrait;
    @BindView(R.id.tv_setting_user_name)
    TextView tvSettingUserName;
    @BindView(R.id.tv_setting_quit_login)
    TextView tvSettingQuitLogin;

    /**
     * --------------------- 上传文件 -----------------
     */
    String TAG = "SettingFragment";

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
        // 判定是否有账户信息
        if (SharedConfigUtil.getToken().isEmpty()) {
            // 显示需要登录
            tvSettingClickLogin.setVisibility(View.VISIBLE);
            // 隐藏账号信息
            tvSettingUserName.setVisibility(View.GONE);
            ivSettingUserPortrait.setVisibility(View.GONE);
            // 隐藏退出登录按钮
            tvSettingQuitLogin.setVisibility(View.GONE);
        } else {
            // 隐藏需要登录
            tvSettingClickLogin.setVisibility(View.GONE);
            // 显示账号信息
            tvSettingUserName.setText(SharedConfigUtil.getUserName());
            tvSettingUserName.setVisibility(View.VISIBLE);
            ivSettingUserPortrait.setVisibility(View.VISIBLE);
            // 显示退出登录按钮
            tvSettingQuitLogin.setVisibility(View.VISIBLE);
        }
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

    @OnClick({R.id.tv_setting_theme, R.id.iv_setting_night_switch, R.id.tv_setting_click_login, R.id.tv_setting_quit_login, R.id.iv_setting_user_portrait})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.tv_setting_click_login:
                // 暂时出现登录dialog
                View loginView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_login, null);
                final EditText etName = loginView.findViewById(R.id.et_user_login_name);
                final EditText etPass = loginView.findViewById(R.id.et_user_login_pass);
                // 历史账号上去
                etName.setText(SharedConfigUtil.getUserName());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setView(loginView)
                        .setTitle("登录弹窗")
                        .setNegativeButton("没有账号，点击注册", ((dialog, which) -> {
                            // 跳转到注册界面

                        }))
                        .setPositiveButton("登录", ((dialog, which) -> {

                            PhoneUser user = new PhoneUser();

                            user.setUserName(etName.getText().toString().trim());
                            user.setUserPass(etPass.getText().toString().trim());

                            if (user.getUserName() == null || user.getUserName().isEmpty()) {
                                Toast.makeText(getActivity().getApplicationContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (user.getUserPass() == null || user.getUserPass().isEmpty()) {
                                Toast.makeText(getActivity().getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            SharedConfigUtil.saveUserName(user.getUserName());

                            fpresenter.handleUserLogin(user);
                            dialog.dismiss();
                        }));
                builder.create().show();
                break;
            case R.id.tv_setting_quit_login:
                // 退出登录流程
                // 隐藏头像 and 用户名
                ivSettingUserPortrait.setVisibility(View.GONE);
                tvSettingUserName.setVisibility(View.GONE);
                // 显示登录按钮
                tvSettingClickLogin.setVisibility(View.VISIBLE);
                // 隐藏退出登录按钮
                tvSettingQuitLogin.setVisibility(View.GONE);
                // 清空token and 用户名
                SharedConfigUtil.saveToken("");
                SharedConfigUtil.saveUserName("");
                break;
            case R.id.iv_setting_user_portrait:
//                 点击头像 -> 上传头像 -> 更换头像
//                setupDialog();

                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .forResult(PictureConfig.CHOOSE_REQUEST);

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
        // 判定是否有账户信息
        if (SharedConfigUtil.getToken().isEmpty()) {
            return;
        }
        // 隐藏需要登录
        tvSettingClickLogin.setVisibility(View.GONE);
        // 显示账号信息
        tvSettingUserName.setText(SharedConfigUtil.getUserName());
        tvSettingUserName.setVisibility(View.VISIBLE);
        ivSettingUserPortrait.setVisibility(View.VISIBLE);
        // 显示退出登录按钮
        tvSettingQuitLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(getContext(), "头像设置失败", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回五种path
                // 1.media.getPath(); 原图path，但在Android Q版本上返回的是content:// Uri类型
                // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路
    //                径；注意：.isAndroidQTransform(false);此字段将返回空
                // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                for (LocalMedia media : selectList) {
                    Log.i(TAG, "压缩::" + media.getCompressPath());
                    Log.i(TAG, "原图::" + media.getPath());
                    Log.i(TAG, "裁剪::" + media.getCutPath());
                    Log.i(TAG, "是否开启原图::" + media.isOriginal());
                    Log.i(TAG, "原图路径::" + media.getOriginalPath());
                    Log.i(TAG, "Android Q 特有Path::" + media.getAndroidQToPath());
                }
                Toast.makeText(context, selectList.get(0).getCutPath(), Toast.LENGTH_SHORT).show();
            break;
            default:
                break;
        }
//        if (mBitmap != null) {
//            mBitmap.recycle();
//        }

//        path = bitmapToBase64(mBitmap);

//        Toast.makeText(context, path, Toast.LENGTH_SHORT).show();

//        mBitmap = BitmapFactory.decodeFile(path);
//        ivSettingUserPortrait.setImageBitmap(mBitmap);

        // 头像上传 -> 上传成功回调显示头像
    }

}
