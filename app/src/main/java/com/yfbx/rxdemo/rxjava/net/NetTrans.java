package com.yfbx.rxdemo.rxjava.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:Edward
 * Date:2018/4/19
 * Description:
 */

public class NetTrans<T> implements Observable.Transformer<NetResult<T>, NetResult<T>> {

    @Override
    public Observable<NetResult<T>> call(Observable<NetResult<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
