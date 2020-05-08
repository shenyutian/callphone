package com.syt.cellphone.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.pojo.Registered;
import com.syt.cellphone.ui.SytMainActivity;
import com.syt.cellphone.util.SharedConfigUtil;
import com.syt.cellphone.widget.SytToolBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册活动
 * @author syt
 */
public class RegisteredActivity extends BaseActivity<RegisterPresenter> implements RegisteredView {


    @BindView(R.id.bar_registered_top)
    SytToolBar barRegisteredTop;
    @BindView(R.id.tv_registered_greet)
    TextView tvRegisteredGreet;
    @BindView(R.id.et_registered_input_name)
    TextInputEditText etRegisteredInputName;
    @BindView(R.id.textInputLayout_registered_input_name)
    TextInputLayout textInputLayoutRegisteredInputName;
    @BindView(R.id.et_registered_input_pass)
    EditText etRegisteredInputPass;
    @BindView(R.id.textInputLayout_registered_input_pass)
    TextInputLayout textInputLayoutRegisteredInputPass;
    @BindView(R.id.et_registered_input_email)
    EditText etRegisteredInputEmail;
    @BindView(R.id.textInputLayout_registered_input_email)
    TextInputLayout textInputLayoutRegisteredInputEmail;
    @BindView(R.id.et_registered_input_verification)
    EditText etRegisteredInputVerification;
    @BindView(R.id.bt_registered_get_verification)
    Button btRegisteredGetVerification;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.bt_registered_submit)
    Button btRegisteredSubmit;
    @BindView(R.id.tv_registered_get_verification_countdown)
    TextView tvRegisteredGetVerificationCountdown;
    @BindView(R.id.textInputLayoutet_registered_input_verification)
    TextInputLayout textInputLayoutetRegisteredInputVerification;
    @BindView(R.id.pb_registered_loading)
    ProgressBar pbRegisteredLoading;

    /**
     * ------------------- 参数介绍 ---------------
     * pattern                  正则表达式的编译表示
     * countDownTimer           倒计时类 防止内存泄漏 需要回收
     */
    private Pattern pattern;
    private CountDownTimer countDownTimer;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    protected void initData() {
        // 重新绘制验证码按钮
        // 验证码 -> 倒计时60s -> 重新发送
        countDownTimer = new CountDownTimer(60000, 1000) {

            private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("s  秒后重新发送");

            @Override
            public void onTick(long millisUntilFinished) {
                tvRegisteredGetVerificationCountdown.setVisibility(View.VISIBLE);
                tvRegisteredGetVerificationCountdown.setText(simpleDateFormat.format(new Date(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                // 显示按钮 ->  关闭倒计时
                tvRegisteredGetVerificationCountdown.setVisibility(View.GONE);
                btRegisteredGetVerification.setVisibility(View.VISIBLE);
                btRegisteredGetVerification.setText("重新发送");
            }


        };

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @OnClick({R.id.bt_registered_get_verification, R.id.bt_registered_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_registered_get_verification:
                // 验证邮箱
                if (verificationEmailInput()) {
                    // 发送验证码
                    presenter.handleVerificationCode(etRegisteredInputEmail.getText().toString().trim());
                    pbRegisteredLoading.setVisibility(View.VISIBLE);

                    // 启动倒计时
                    countDownTimer.start();
                    // 设置验证码图标变换
                    btRegisteredGetVerification.setVisibility(View.GONE);
                    tvRegisteredGetVerificationCountdown.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.bt_registered_submit:
                // 验证输入框 + 注册
                if (verificationAllInput()) {
                    PhoneUser user = new PhoneUser();

                    user.setUserName(etRegisteredInputName.getText().toString().trim());
                    user.setUserPass(etRegisteredInputPass.getText().toString().trim());
                    user.setUserEmail(etRegisteredInputEmail.getText().toString().trim());
                    user.setVerificationNumber(etRegisteredInputVerification.getText().toString().trim());

                    presenter.handRegister(user);
                    pbRegisteredLoading.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 验证所有输入框是否合法
     */
    private boolean verificationAllInput() {
        // 验证用户名
        if (!verificationUserName()) {
            return false;
        }

        // 验证密码
        if (TextUtils.isEmpty(etRegisteredInputPass.getText().toString().trim())) {
            etRegisteredInputPass.setError("密码不能为空");
            return false;
        }
        if (etRegisteredInputPass.getText().toString().trim().length() < 6) {
            etRegisteredInputPass.setError("密码不得低于6位");
            return false;
        }
        if (etRegisteredInputPass.getText().toString().trim().length() > 30) {
            etRegisteredInputPass.setError("密码长度太长了");
            return false;
        }

        // 验证邮箱格式
        if (!verificationEmailInput()) {
            return false;
        }

        // 验证验证码
        if (TextUtils.isEmpty(etRegisteredInputVerification.getText().toString().trim())) {
            etRegisteredInputVerification.setError("验证码不为空!");
            return false;
        }
        if (etRegisteredInputVerification.getText().toString().trim().length() != 4) {
            etRegisteredInputPass.setError("密码长度是四位");
            return false;
        }
        return true;
    }

    /**
     * 验证邮箱是否合法
     */
    private boolean verificationEmailInput() {
        if (TextUtils.isEmpty(etRegisteredInputEmail.getText().toString())) {
            etRegisteredInputEmail.setError("邮箱不能为空");
            return false;
        }
        if (etRegisteredInputEmail.getText().toString().length() > 30) {
            etRegisteredInputEmail.setError("邮箱长度不合法");
            return false;
        }
        //查找邮箱
        //mailRegex是整体邮箱正则表达式，mailName是@前面的名称部分，mailDomain是后面的域名部分
        String mailRegex, mailName, mailDomain;
        //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
        mailName = "^[0-9a-z]+\\w*";
        //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
        mailDomain = "([0-9a-z]+\\.)+[0-9a-z]+$";
        //邮箱正则表达式      ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
        mailRegex = mailName + "@" + mailDomain;
        pattern = Pattern.compile(mailRegex);
        // 正则式验证
        if (pattern.matcher(etRegisteredInputEmail.getText().toString().trim()).matches()) {
            return true;
        } else {
            etRegisteredInputEmail.setError("邮箱格式不合法");
            return false;
        }
    }

    private boolean verificationUserName() {
        // 验证用户名
        if (TextUtils.isEmpty(etRegisteredInputName.getText().toString().trim())) {
            etRegisteredInputName.setError("用户名不能为空");
            return false;
        }
        if (etRegisteredInputName.getText().toString().trim().length() > 10) {
            etRegisteredInputName.setError("用户名长度不合法");
            return false;
        }
        String mailRegex = "[\u4e00-\u9fa5]+";
        pattern = Pattern.compile(mailRegex);
        if (pattern.matcher(etRegisteredInputName.getText().toString().trim()).matches()) {
            etRegisteredInputName.setError("用户名不能是中文");
            return false;
        }
        return true;
    }

    /**
     * 注册成功后，跳出过渡动画，然后关闭注册 activity 跳转到首页
     */
    @Override
    public void registeredSuccess(Registered user) {

        pbRegisteredLoading.setVisibility(View.GONE);
        // 成功操作 保存用户名
        SharedConfigUtil.saveUserName(user.getUserName());
        // 保存返回的token
        SharedConfigUtil.saveUserName(user.getToken());

        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();

        // 跳转至首页个人中心
        Config.setBottomMenu(4);
        Intent intent = new Intent(getApplicationContext(), SytMainActivity.class);
        intent.putExtra("param", 4);
        startActivity(intent);
    }

    /**
     * 注册失败后，跳出提示。
     */
    @Override
    public void registeredError(Registered error) {
        pbRegisteredLoading.setVisibility(View.GONE);

        Registered.ErrorMapBean errorMap = error.getErrorMap();

        // 错误提示消息
        etRegisteredInputName.setError(errorMap.getNameError());
        etRegisteredInputPass.setError(errorMap.getPassError());
        etRegisteredInputEmail.setError(errorMap.getEmailError());
        etRegisteredInputVerification.setError(errorMap.getErrorVerNum());

    }

    /**
     * 发送邮件失败
     */
    @Override
    public void emailError(String msg) {
        pbRegisteredLoading.setVisibility(View.GONE);

        etRegisteredInputEmail.setError(msg);
    }

    /**
     * 发送邮件成功
     */
    @Override
    public void emailSuccess() {
        pbRegisteredLoading.setVisibility(View.GONE);
    }


}
