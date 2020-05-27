package com.syt.cellphone.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.ui.setting.feedback.FeedbackActivity;
import com.syt.cellphone.ui.user.RegisteredActivity;
import com.syt.cellphone.util.AnimatorScrollUtil;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.util.ToastUtil;
import com.syt.cellphone.widget.GlideEngine;
import com.tencent.bugly.beta.Beta;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 第四个界面
 */
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
    @BindView(R.id.constraintLayout_setting_person)
    ConstraintLayout constraintLayoutSettingPerson;
    @BindView(R.id.constraintLayout_setting_theme)
    ConstraintLayout constraintLayoutSettingTheme;
    @BindView(R.id.constraintLayout_setting_update)
    ConstraintLayout constraintLayoutSettingUpdate;
    @BindView(R.id.constraintLayout_setting_help)
    ConstraintLayout constraintLayoutSettingHelp;
    @BindView(R.id.constraintLayout_setting_about)
    ConstraintLayout constraintLayoutSettingAbout;
    @BindView(R.id.constraintLayout_setting_introduce)
    ConstraintLayout constraintLayoutSettingIntroduce;
    @BindView(R.id.constrainLayout_setting_all)
    ConstraintLayout constrainLayoutSettingAll;
    @BindView(R.id.constraintLayout_setting_bottom)
    ConstraintLayout constraintLayoutSettingBottom;
    @BindView(R.id.bar_setting_title)
    Toolbar barSettingTitle;


    /**
     * --------------------- 上传文件 -----------------
     * alertDialog      登录弹窗
     */
    String TAG = "SettingFragment";
    private AlertDialog alertDialog;
    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        // 设置标题栏
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(barSettingTitle);

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
            // 有图片就显示头像
            if (!SharedConfigUtil.getPortrait().isEmpty()) {
                Glide.with(context)
                        .load(SharedConfigUtil.getPortrait())
                        .into(ivSettingUserPortrait);
            }
            // 显示退出登录按钮
            tvSettingQuitLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 标题栏设置
     * @param menu 标题内容
     * @param inflater 标题设置
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.setting_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_setting_admin:
                ToastUtil.makeText("管理员界面");
                break;
            case R.id.item_setting_author:
                ToastUtil.makeText("打开作者详情");
                break;
            default:
                break;
        }
        return true;
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

    @OnClick({R.id.tv_setting_theme, R.id.iv_setting_night_switch, R.id.tv_setting_click_login, R.id.tv_setting_quit_login, R.id.iv_setting_user_portrait, R.id.constraintLayout_setting_update, R.id.constraintLayout_setting_help, R.id.constraintLayout_setting_about, R.id.constraintLayout_setting_introduce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_night_switch:
                // 点击主题切换
                handSettingNight();
                break;
            case R.id.tv_setting_click_login:
                // 登录点击
                handLogin();
                break;
            case R.id.tv_setting_quit_login:
                // 退出登录点击
                handQuitLogin();
                break;
            case R.id.iv_setting_user_portrait:
                // 头像点击
                handSetPortrait();
                break;
            case R.id.constraintLayout_setting_update:
                // 更新点击
                Beta.checkUpgrade(true, false);
                Toast.makeText(context, "已经是最新版本了", Toast.LENGTH_LONG).show();
                break;
            case R.id.constraintLayout_setting_help:
                // 帮助反馈点击
                Intent intent = new Intent(context, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.constraintLayout_setting_about:
                Toast.makeText(context, "作者：syt", Toast.LENGTH_SHORT).show();
                // 关于
                break;
            case R.id.constraintLayout_setting_introduce:
                // 使用帮助
                Toast.makeText(context, "请点击反馈", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 查询夜间模式开关，来确定视图
     */
    private void switchNightOnOff() {
        if (SharedConfigUtil.getNightOnOff() == 2) {
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
        // 关闭登录框
        alertDialog.dismiss();
        // 隐藏需要登录
        tvSettingClickLogin.setVisibility(View.GONE);
        // 显示账号信息
        tvSettingUserName.setText(SharedConfigUtil.getUserName());
        tvSettingUserName.setVisibility(View.VISIBLE);
        ivSettingUserPortrait.setVisibility(View.VISIBLE);
        // 显示退出登录按钮
        tvSettingQuitLogin.setVisibility(View.VISIBLE);
        // 有图片就显示头像
        if (!SharedConfigUtil.getPortrait().isEmpty()) {
            Glide.with(context)
                    .load(SharedConfigUtil.getPortrait())
                    .into(ivSettingUserPortrait);
        }
    }

    @Override
    public void refreshPortrait(String imgSrc) {
        Glide.with(this)
                .load(imgSrc)
                .into(ivSettingUserPortrait);
        SharedConfigUtil.savePortrait(imgSrc);
    }

    /**
     * 点击黑夜or白天事件
     */
    private void handSettingNight() {
        if (SharedConfigUtil.getNightOnOff() == 2) {
            //夜间 切换 日间
            SharedConfigUtil.saveNightOnOff(1);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            //日间 切换 夜间
            SharedConfigUtil.saveNightOnOff(2);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    /**
     * 处理登录
     */
    private void handLogin() {
        // 暂时出现登录dialog
        View loginView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_login, null);
        final EditText etName = loginView.findViewById(R.id.et_user_login_name);
        final EditText etPass = loginView.findViewById(R.id.et_user_login_pass);
        final Button btSubmit = loginView.findViewById(R.id.bt_user_login_submit);
        final TextView btRequest = loginView.findViewById(R.id.tv_user_login_request);
        // 历史账号上去
        etName.setText(SharedConfigUtil.getUserName());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setView(loginView)
                .setNegativeButton("取消", ((dialog, which) -> {
                    dialog.cancel();
                }))
                .setPositiveButton("登录", null);
        alertDialog = builder.create();

        // 重写onShow()方法 里面的getButton
        alertDialog.setOnShowListener((DialogInterface dialogInterface) -> {

            Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

            button.setOnClickListener((View v) -> {
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
            });
        });

        // 验证登录
        btSubmit.setOnClickListener((v -> {
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
        }));

        // 视图覆盖监听
        loginView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect loginRect = new Rect();
            loginView.getWindowVisibleDisplayFrame(loginRect);

            int[] leftTop = new int[2];
            btSubmit.getLocationOnScreen(leftTop);

            // 被遮挡的高度
            int scrheight = loginView.getRootView().getHeight() - loginRect.bottom;

            // 键盘弹出
            if (scrheight > 140) {
                scrheight += 10;
                if (loginView.getScaleY() != scrheight && scrheight > 0) {
                    AnimatorScrollUtil.scrollTo(loginView, 0, scrheight);
                }

            } else {
                // 视图偏移修正
                if (loginView.getScaleY() != 0) {
                    AnimatorScrollUtil.scrollTo(loginView, 0, 0);
                }
            }
        });

        btRequest.setOnClickListener(v -> {
            // 跳转到注册界面
            startActivity(new Intent(getContext(), RegisteredActivity.class));
        });

        alertDialog.show();
    }

    /**
     * 退出登录
     */
    private void handQuitLogin() {
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
    }

    /**
     * 头像上传
     */
    private void handSetPortrait() {
        //                 点击头像 -> 上传头像 -> 更换头像
//                setupDialog();

        // 参考 https://github.com/LuckSiege/PictureSelector/wiki/PictureSelector-%E5%8A%9F%E8%83%BD%E9%85%8D%E5%88%B6%E9%A1%B9
        PictureSelector.create(getActivity())
                // 传入图片
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                // 多选 or 单选 2 ? 1
                .selectionMode(1)
                // 单选模式下直接返回
                .isSingleDirectReturn(true)
                // 开启压缩
                .compress(true)
                // 低于200k不压缩
                .minimumCompressSize(200)
                // 是否裁剪
                .enableCrop(true)
                // 开启圆形裁剪
                .circleDimmedLayer(true)
                // 裁切比例
                .withAspectRatio(1, 1)
                // 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropFrame(false)
                // 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .showCropGrid(false)
                // 支持android q 的沙盒
                .isAndroidQTransform(true)
                // 回调
                .forResult(new OnResultCallbackListener() {
                    @Override
                    public void onResult(List<LocalMedia> selectList) {
                        // 图片选择结果回调
                        // 例如 LocalMedia 里面返回五种path
                        // 1.media.getPath(); 原图path，但在Android Q版本上返回的是content:// Uri类型
                        // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                        // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                        // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                        // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路
                        //                径；注意：.isAndroidQTransform(false);此字段将返回空
                        // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                        File portraitFile = null;
                        for (LocalMedia media : selectList) {
                            Log.i(TAG, "压缩::" + media.getCompressPath());
                            Log.i(TAG, "原图::" + media.getPath());
                            Log.i(TAG, "裁剪::" + media.getCutPath());
                            Log.i(TAG, "是否开启原图::" + media.isOriginal());
                            Log.i(TAG, "原图路径::" + media.getOriginalPath());
                            Log.i(TAG, "Android Q 特有Path::" + media.getAndroidQToPath());
                            portraitFile = new File(media.getAndroidQToPath());
                        }
                        fpresenter.uploadProtrait(portraitFile);
                    }

                    @Override
                    public void onCancel() {
                        Logger.d("头像设置失败");
                    }
                });

    }
}
