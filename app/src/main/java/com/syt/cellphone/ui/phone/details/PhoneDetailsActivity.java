package com.syt.cellphone.ui.phone.details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.syt.cellphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneDetailsActivity extends AppCompatActivity {

    @BindView(R.id.rv_details_data)
    RecyclerView rvDetailsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);
        ButterKnife.bind(this);
        // 沉浸式布局
        ImmersionBar.with(this).init();
    }


}
