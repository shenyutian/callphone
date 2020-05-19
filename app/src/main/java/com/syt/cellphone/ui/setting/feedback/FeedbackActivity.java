package com.syt.cellphone.ui.setting.feedback;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseActivity;

import butterknife.BindView;

/**
 * 反馈功能视图
 *
 * @author syt
 * @data 2020.5.8
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackView {

    @BindView(R.id.tv_feedback_submit)
    TextView tvFeedback;
    @BindView(R.id.et_feedback_title)
    TextInputEditText etFeedbackTitle;
    @BindView(R.id.textInputLayout_feedback_title)
    TextInputLayout textInputLayoutFeedbackTitle;
    @BindView(R.id.rv_feedback_ic_list)
    RecyclerView rvFeedbackIcList;
    @BindView(R.id.et_feedback_content)
    TextInputEditText etFeedbackContent;
    @BindView(R.id.textInputLayout_feedback_content)
    TextInputLayout textInputLayoutFeedbackContent;

    @Override
    protected FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {
//        richEditTextFeedbackConnect.hand
    }

    @Override
    public Context getContext() {
        return this;
    }

}
