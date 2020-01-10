package com.syt.cellphone.base;

import android.content.Context;

import com.syt.cellphone.net.ApiRetrofit;
import com.syt.cellphone.net.ApiServer;
import com.syt.cellphone.net.BaseObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author：syt
 * Date: 2019-11-22
 * 作用: 基础 p 数据处理
 */
public class BasePresenter<V extends BaseView> {

    /**
     * CompositeDisposable可以将Disposable统一管理
     */
    private CompositeDisposable compositeDisposable;
    public V baseView;
    public Context context;

    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
        this.context = baseView.getContext();
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    /**
     * @return 当前视图
     */
    public V getBaseView() {
        return baseView;
    }

    /**
     * 网络请求方法
     * @param observable 被观察者
     * @param observer  观察者
     * @param delaytime 延迟时间
     */
    public void addDisposable(Observable<?> observable, BaseObserver observer, final int delaytime) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        if (delaytime == 0) {
            // 新线程 + 主线程 + 作用对象(观察者)
            compositeDisposable.add(
                    observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer));

        } else {
            // repeatWhen() 有条件地、重复发送被观察者事件
            compositeDisposable.add(observable.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                    return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {
                            // 注：此处加入了delay操作符，作用 = 延迟一段时间发送，以实现轮询间间隔设置
                            return Observable.just(1).delay(delaytime, TimeUnit.SECONDS);

                            // 延时一分钟轮询
                            //return Observable.timer(delaytime, TimeUnit.MILLISECONDS);
                        }
                    });
                }
            }).subscribeOn(Schedulers.io())
                    .repeat() // 重复
                    .retry() // 重试
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer));
        }

    }

    public void removeDisposable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();

        }
    }

}
