package com.syt.cellphone.ui.soc;

import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syt.cellphone.R;
import com.syt.cellphone.base.BaseBean;
import com.syt.cellphone.base.BaseFragment;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.util.ToastUtil;

import java.util.Objects;

import butterknife.BindView;
import jp.wasabeef.blurry.Blurry;

/**
 * soc 处理器 列表
 * @author space
 * @data 19/12/11
 */
public class SocFragment extends BaseFragment<SocPresenter> implements SocView {


    @BindView(R.id.rv_soc_list)
    RecyclerView rvSocList;
    @BindView(R.id.srl_soc_handle)
    SwipeRefreshLayout srlSocHandle;
    @BindView(R.id.dl_soc_msg)
    DrawerLayout dlSocMsg;
    private SocAdapterNew socAdapter;
    private TextView tvFotter;

    @Override
    protected SocPresenter initPresenter() {
        return new SocPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_soc;
    }

    @Override
    protected void initData() {
        fpresenter.getNetSocList(false);
        // Rv处理
        initRv();
    }

    /**
     * 插入布局数据
     */
    private void initRv() {
        // 设置布局方式
        rvSocList.setLayoutManager(new LinearLayoutManager(getContext()));
        // 将数据 和环境context扔进去
        socAdapter = new SocAdapterNew(R.layout.soc_list_item, fpresenter.getSocList());
        // 点击事件
        socAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            Soc soc = socAdapter.getItem(position);
            ToastUtil.makeText("点击编号 " + soc.getSocId() + ",名称: " + soc.getSocName());
            dialogSoc(soc);
            Blurry.with(context).radius(10).sampling(2).onto(dlSocMsg);
        });
        // 上拉事件
        socAdapter.setOnLoadMoreListener(() ->
                fpresenter.getNetSocList(true)
                , rvSocList);
        // 执行适配器
        rvSocList.setAdapter(socAdapter);
        // 下拉刷新
        srlSocHandle.setOnRefreshListener(() -> {
            fpresenter.getNetSocList(false);
        });
        // 添加底部布局
        tvFotter = new TextView(context);
        tvFotter.setText("我也是有底线的");
        tvFotter.setVisibility(View.GONE);
        socAdapter.addFooterView(tvFotter);
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

    /**
     *  刷新rv
     */
    @Override
    public void refreshRv() {
        //刷新界面
        socAdapter.notifyDataSetChanged();
        // 关闭下面的刷新
        socAdapter.loadMoreComplete();
        // 关闭上面的刷新
        srlSocHandle.setRefreshing(false);
    }

    /**
     * 底部显示
     */
    @Override
    public void showNoData() {
        // 关闭下面的刷新
        socAdapter.loadMoreComplete();
        tvFotter.setVisibility(View.INVISIBLE);

    }

    /**
     * soc详情弹窗
     * @param soc 处理器内容
     */
    private void dialogSoc(final Soc soc) {
        // 弹窗创建
        final AlertDialog toastDialog = new AlertDialog.Builder(context, R.style.DialogStyle).create();
        // 弹窗显示
        toastDialog.show();
        // 获取当前窗口
        Window window = toastDialog.getWindow();
        // 居中
        Objects.requireNonNull(window).setGravity(Gravity.CENTER);
        // 布局显示
        WindowManager.LayoutParams lp = window.getAttributes();
        // 宽高
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setContentView(R.layout.dialog_toast_soc_details);

        TextView tvSocName = window.findViewById(R.id.tv_soc_name);
        tvSocName.setText(soc.getSocName());
        TextView tvSocTrademark = window.findViewById(R.id.tv_soc_trademark);
        tvSocTrademark.setText(soc.getSocTrademark());
        TextView tvSocProcess = window.findViewById(R.id.tv_soc_process);
        tvSocProcess.setText(soc.getSocProcess());
        TextView tvSocProperty = window.findViewById(R.id.tv_soc_property);
        tvSocProperty.setText(soc.getSocProperty());
        TextView tvSocBbx = window.findViewById(R.id.tv_soc_bbx);
        tvSocBbx.setText(soc.getSocBbx());
        TextView tvSocCpu = window.findViewById(R.id.tv_soc_cpu_specification);
        tvSocCpu.setText(soc.getSocCpuSpecification());
        TextView tvsocGpu = window.findViewById(R.id.tv_gpu_specification);
        tvsocGpu.setText(soc.getSocGpuSpecification());

        toastDialog.setOnCancelListener((DialogInterface p) -> {
            Blurry.delete(dlSocMsg);
        });
    }
}
