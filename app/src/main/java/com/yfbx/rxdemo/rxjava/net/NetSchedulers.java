package com.yfbx.rxdemo.rxjava.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:Edward
 * Date:2018/4/19
 * Description:
 */

public class NetSchedulers<T> implements Observable.Transformer<NetResult<T>, NetResult<T>> {


    public static <T> NetSchedulers<T> ioToUi() {
        return new NetSchedulers<T>();
    }

    @Override
    public Observable<NetResult<T>> call(Observable<NetResult<T>> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
