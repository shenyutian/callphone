package com.syt.cellphone.ui.phone.details;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.net.BaseObserver;
import com.syt.cellphone.pojo.PhoneDetails;

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
