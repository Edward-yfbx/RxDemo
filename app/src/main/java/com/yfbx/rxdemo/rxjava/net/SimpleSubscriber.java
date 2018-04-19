package com.yfbx.rxdemo.rxjava.net;

import rx.Subscriber;

/**
 * Author:Edward
 * Date:2018/4/19
 * Description:
 */

public abstract class SimpleSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

}
