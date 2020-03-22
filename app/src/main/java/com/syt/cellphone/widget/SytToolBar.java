package com.syt.cellphone.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.syt.cellphone.R;
import com.syt.cellphone.ui.SytMainActivity;

/**
 * @author shenyutian
 * @data 2020-01-19 10:57
 * 功能 自定义导航栏
 */
public class SytToolBar extends ConstraintLayout {

    /**
     * isLeftBtnVisible     左边返回按钮是否显示
     * leftResld            左边按钮id
     * isLeftTvVisible      左边文本是否显示
     * leftTvText           左边文本是否显示
     * 。。。。。。
     */
    private Boolean isLeftBtnVisible;
    private int leftResld;
    private Boolean isLeftTvVisible;
    private String leftTvText;

    private Boolean isRightBtnVisible;
    private int rightResId;
    private Boolean isRightTvVisible;
    private String rightTvText;

    /**
     * isTitleVisible       标题是否显示
     * titleText            标题内容
     * isContentVisible     输入框是否显示
     * contentText          输入框内容
     */
    private Boolean isTitleVisible;
    private String titleText;
    private Boolean isContentVisible;
    private String contentText;

    private int backgroundResId;

    /**
     * --------------------控件列表-------------------
     */
    private TextView titleTv;

    public SytToolBar(Context context) {
        this(context, null);
    }

    public SytToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SytToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
        /**
         * 左边按钮 + 文本 属性
         */
        isLeftBtnVisible = typedArray.getBoolean(R.styleable.CustomToolBar_left_btn_visible, false);
        leftResld = typedArray.getResourceId(R.styleable.CustomToolBar_left_btn_src, -1);
        isLeftTvVisible = typedArray.getBoolean(R.styleable.CustomToolBar_left_tv_visible, false);
        if (typedArray.hasValue(R.styleable.CustomToolBar_left_tv_text)) {
            leftTvText = typedArray.getString(R.styleable.CustomToolBar_left_tv_text);
        }

        /**-------------获取右边按钮属性------------*/
        isRightBtnVisible = typedArray.getBoolean(R.styleable.CustomToolBar_right_btn_visible, false);
        rightResId = typedArray.getResourceId(R.styleable.CustomToolBar_right_btn_src, -1);
        /**-------------获取右边文本属性------------*/
        isRightTvVisible = typedArray.getBoolean(R.styleable.CustomToolBar_right_tv_visible, false);
        if(typedArray.hasValue(R.styleable.CustomToolBar_right_tv_text)){
            rightTvText = typedArray.getString(R.styleable.CustomToolBar_right_tv_text);
        }

        /**-------------获取标题属性------------*/
        isTitleVisible = typedArray.getBoolean(R.styleable.CustomToolBar_title_visible, false);
        if(typedArray.hasValue(R.styleable.CustomToolBar_title_text)){
            titleText = typedArray.getString(R.styleable.CustomToolBar_title_text);
        }
        /**
         * 标题框属性 输入框
         */
        isContentVisible = typedArray.getBoolean(R.styleable.CustomToolBar_content_visible, false);
        if(typedArray.hasValue(R.styleable.CustomToolBar_content_text)){
            contentText = typedArray.getString(R.styleable.CustomToolBar_content_text);
        }
        /**-------------背景颜色------------*/
        if (typedArray.hasValue(R.styleable.CustomToolBar_barBackground)) {
            backgroundResId = typedArray.getResourceId(R.styleable.CustomToolBar_barBackground, -1);
        }
        // 回收typedArray
        typedArray.recycle();
        /**
         * 布局控件注册
         */
        View barLayoutView = View.inflate(getContext(), R.layout.layout_common_titlebar, null);

        Button leftBtn = barLayoutView.findViewById(R.id.toolbar_left_btn);
        TextView leftTv = barLayoutView.findViewById(R.id.toolbar_left_tv);

        titleTv = barLayoutView.findViewById(R.id.toolbar_title_tv);
        EditText contentTv = barLayoutView.findViewById(R.id.toolbar_content_et);

        Button rightBtn = barLayoutView.findViewById(R.id.toolbar_right_btn);
        TextView rightTv = barLayoutView.findViewById(R.id.toolbar_right_tv);

        /**
         *  左边的控件属性设置
         */
        if (isLeftBtnVisible) {
            leftBtn.setVisibility(isLeftBtnVisible == true ? VISIBLE : GONE);
            leftBtn.setBackgroundResource(leftResld != -1 ? leftResld : R.drawable.top_back);
        }
        leftTv.setVisibility(isLeftTvVisible == true ? VISIBLE : GONE);
        leftBtn.setOnClickListener(v -> {
            // 如果当前 context 是 Activity . 就可以退出活动
            if (getContext() instanceof Activity) {
                ((Activity) getContext()).finish();
            }
        });
        leftTv.setOnClickListener(v -> {
            // 如果当前 context 是 Activity . 就可以退出活动
            if (getContext() instanceof Activity) {
                ((Activity) getContext()).finish();
            }
        });
        // 设置左边的文字
        if (isLeftTvVisible) {
            leftTv.setVisibility(isLeftTvVisible == true ? VISIBLE : GONE);
            leftTv.setText(leftTvText);
        }

        /**
         * 中间标题设置
         */
        if (isTitleVisible) {
            titleTv.setVisibility(VISIBLE);
            titleTv.setText(titleText == null ? "标题" : titleText);
        }
        if (isContentVisible) {
            contentTv.setVisibility(VISIBLE);
            contentTv.setHint(contentText);
        }


//        titleTv.setVisibility(isTitleVisible == true ? VISIBLE : GONE);
//        contentTv.setVisibility(isContentVisible == true ? VISIBLE : GONE);

//        rightBtn.setVisibility(isRightBtnVisible == true ? VISIBLE : GONE);
//        rightBtn.setBackgroundResource(rightResId != -1 ? rightResId : null);
//        rightTv.setVisibility(isRightTvVisible == true ? VISIBLE : GONE);

//        if(backgroundResId != -1){
//            barRlyt.setBackgroundColor(backgroundResId);
//        }

        /**
         * ---------------设置右边的属性------------
         */
        if (isRightTvVisible) {
            rightTv.setText(rightTvText);
            // 右边的点击事件返回首页
            rightTv.setOnClickListener( v -> {
                getContext().startActivity(new Intent(getContext(), SytMainActivity.class));
            });
        }

        addView(barLayoutView, 0);
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        titleTv.setText(titleText);
    }
}
