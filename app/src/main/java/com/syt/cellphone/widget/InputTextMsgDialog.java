package com.syt.cellphone.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialog;

import com.syt.cellphone.R;

/**
 * @author shenyutian
 * @data 2020/3/4 11:41 AM
 * 功能 评论的弹窗  参考 https://blog.csdn.net/qq_32518491/article/details/85000421
 */
public class InputTextMsgDialog extends AppCompatDialog {

    /**
     * imm                      输入键盘管理
     * messageTextView          输入文本
     * confirmBth               确认提交按钮
     * tvNumber                 文本数量
     */
    private Context             context;
    private InputMethodManager  imm;
    private EditText            messageTextView;
    private TextView            confirmBth;
    private RelativeLayout      rlDlg;
    private int                 mLastDiff = 0;
    private TextView            tvNumber;
    private int                 maxNumber = 200;

    /**
     * 文本输入回调接口
     */
    public interface OnTextSendListener {
        /**
         * 文本输入 回调的回调方法
         * @param msg
         */
        void onTextSend(String msg);
    }

    private OnTextSendListener onTextSendListener;

    public InputTextMsgDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        // 自定义动画
        this.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        init();
        setLayout();
    }

    private void init() {
        setContentView(R.layout.dialog_estimate);
        messageTextView = findViewById(R.id.et_input_message);
        tvNumber = findViewById(R.id.tv_input_number);
        final LinearLayout rldlgview = findViewById(R.id.rl_inputdlg_view);
        confirmBth = findViewById(R.id.bt_input_submit);

        // 监听文本框
        messageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvNumber.setText(s.length() + "/" + maxNumber);
                // 设置输入框超过了，变颜色
                if (s.length() > maxNumber) {
                    tvNumber.setTextColor(Color.RED);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tvNumber.setTextColor(context.getResources().getColor(R.color.base_color, null));
                    } else {
                        tvNumber.setTextColor(context.getResources().getColor(R.color.base_color));
                    }
                }
                // 设置输入框有内容，就可以变色了
                if (s.length() == 0) {
                    confirmBth.setBackground(context.getResources().getDrawable(R.drawable.shape_buttom_background_selected));
                } else {
                    confirmBth.setBackground(context.getResources().getDrawable(R.drawable.shape_button_background));
                }
            }
        });

        // 注册键盘监听器
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        // 点击关闭
        rlDlg = findViewById(R.id.rl_outside_view);
        rlDlg.setOnClickListener(v -> {
            if (v.getId() != R.id.rl_inputdlg_view) {
                dismiss();
            }
        });

        // 提交按钮操作
        confirmBth.setOnClickListener(
                new OnMultClickListener() {
                    @Override
                    public void onMultClick(View v) {
                        String msg = messageTextView.getText().toString().trim();
                        if (msg.length() > maxNumber) {
                            Toast.makeText(getContext(), "超过最大字数限制", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (msg.isEmpty()) {
                            Toast.makeText(getContext(), "请输入文字", Toast.LENGTH_SHORT).show();
                        } else {
                            // 输入回调，关闭输入法
                            onTextSendListener.onTextSend(msg);
                            imm.showSoftInput(messageTextView, InputMethodManager.SHOW_FORCED);
                            //隐藏键盘
                            imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                            messageTextView.setText("");
                            // 关闭窗口
                            dismiss();
                        }
                        messageTextView.setText(null);
                    }
                });
        // 监听视图加载完毕
        rldlgview.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            // 创建一个空矩形
            Rect rect = new Rect();
            // 获得屏幕可视部分 getWindowVisibleDisplayFrame 它是用来获取当前窗口可视区域大小的
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            // 获取屏幕高度
            int screenHeight = getWindow().getDecorView().getRootView().getHeight();
            // 此处就是用来获得键盘高度的，在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
            int heightDifference = screenHeight - rect.bottom;
            if (heightDifference <= 0 && mLastDiff > 0) {
                dismiss();
            }
            mLastDiff = heightDifference;
        });

        // 点击关闭输入框
        rldlgview.setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
            dismiss();
        });

        // 监听键盘的关闭时间
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
                    dismiss();
                return false;
            }
        });
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     * 暴露给外部的回调接口
     * @param onTextSendListener 文本监听类
     */
    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.onTextSendListener = onTextSendListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mLastDiff = 0;
    }
}
