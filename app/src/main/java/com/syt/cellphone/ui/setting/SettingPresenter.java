package com.syt.cellphone.ui.setting;

import android.widget.Toast;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.util.SharedConfigUtil;

/**
 * @author：syt Date: 2019-12-09
 * 作用: 设置
 */
public class SettingPresenter extends BasePresenter<SettingView> {


    public SettingPresenter(SettingView baseView) {
        super(baseView);
    }

    /**
     * 处理登录逻辑
     * @param user 用户登录数据
     */
    public void handleUserLogin(PhoneUser user) {
        addDisposable(apiServer.setUserLogin(user), new BaseObserver<PhoneUser>(baseView) {
            @Override
            public void onSuccess(PhoneUser o) {
                Toast.makeText(getBaseView().getContext().getApplicationContext(), "msg: " + o.getMessage(), Toast.LENGTH_SHORT).show();
                if ("登录成功".equals(o.getMessage())) {
                    SharedConfigUtil.saveToken(o.getToken());
                    baseView.refresh();
                }
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }, 0);
    }

    public void uploadProtrait() {
//        apiServer.unload();
    }
}
