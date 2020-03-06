package com.syt.cellphone.ui.phone.details;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.Estimate;
import com.syt.cellphone.pojo.PhoneDetails;
import com.syt.cellphone.util.LogUtil;
import com.syt.cellphone.util.ToastUtil;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author shenyutian
 * @data 2020-01-19 17:37
 * 功能
 */
public class PhoneDetailsPresenter extends BasePresenter<PhoneDetailsView> {

    private PhoneDetails data;

    public PhoneDetailsPresenter(PhoneDetailsView baseView) {
        super(baseView);
    }

    public PhoneDetails getData() {
        return data;
    }

    public void handlePhoneDetails(int phoneId) {
        if (phoneId == 0) {
            LogUtil.e("暂无设备");
            // 提示暂无设备

        } else {
            addDisposable(apiServer.getPhoneDetailsById(phoneId),
                    new BaseObserver<PhoneDetails>(baseView) {
                        @Override
                        public void onSuccess(PhoneDetails o) {
                            data = o;
                            baseView.resetPhoneDetails();
                        }

                        @Override
                        public void onError(String msg) {

                        }
                    }, 0);
        }
    }

    public void handUnloadEstimate(Estimate estimate) {
        if (estimate == null) {
            Logger.e("评价不能为空");
            return;
        }
        // 还需要一个请求头，来发送token。
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(estimate));

        addDisposable(apiServer.setEstimate(body), new BaseObserver<String>(baseView) {

            @Override
            public void onSuccess(String o) {
                ToastUtil.makeText(o);
                // 重新请求，刷新整个布局
                handlePhoneDetails(estimate.getPhoneId());
            }

            @Override
            public void onError(String msg) {

            }
        }, 0);
    }
}
