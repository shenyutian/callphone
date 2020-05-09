package com.syt.cellphone.ui.setting.feedback;

import com.syt.cellphone.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shenyutian
 * @data 2020/5/8 4:07 PM
 * 功能 反馈指挥者
 */
public class FeedbackPresenter extends BasePresenter<FeedbackView> {

    public FeedbackPresenter(FeedbackView baseView) {
        super(baseView);
    }

    /**
     * 本地反馈
     */
    public void test() {
        Observable
                .create(new ObservableOnSubscribe<String>() {

                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("下一步");
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
