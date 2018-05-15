package com.yfbx.rxdemo.net.subscriber;

import com.yfbx.rxdemo.rxbus.EventSubscriber;
import com.yfbx.rxdemo.rxbus.RxBus;
import com.yfbx.rxdemo.rxbus.event.ProgressEvent;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public abstract class FileSubscriber<T> extends Subscriber<T> {

    private Subscription subscription;

    public FileSubscriber() {
        onProgress();
    }

    @Override
    public void onCompleted() {
        subscription.unsubscribe();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        subscription.unsubscribe();
    }

    public abstract void updateProgress(int percent);


    public void onProgress() {
        subscription = RxBus.getDefault()
                .toObservable(ProgressEvent.class)
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EventSubscriber<ProgressEvent>() {
                    @Override
                    protected void onEvent(ProgressEvent event) {
                        updateProgress(event.percent);
                    }
                });
    }
}
