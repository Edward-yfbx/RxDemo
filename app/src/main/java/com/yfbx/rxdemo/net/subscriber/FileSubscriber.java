package com.yfbx.rxdemo.net.subscriber;


import com.yfbx.rxdemo.rxbus.event.FileLoadEvent;
import com.yfbx.rxdemo.rxbus.EventSubscriber;
import com.yfbx.rxdemo.rxbus.RxBus;

import rx.Subscriber;
import rx.Subscription;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public abstract class FileSubscriber<T> extends Subscriber<T> {

    private Subscription subscription;

    public FileSubscriber() {
        subscribeLoadProgress();
    }

    @Override
    public void onCompleted() {
        subscription.unsubscribe();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    public abstract void updateProgress(long progress, long total);


    public void subscribeLoadProgress() {
        subscription = RxBus.registerEvent(FileLoadEvent.class, new EventSubscriber<FileLoadEvent>() {
            @Override
            protected void onEvent(FileLoadEvent fileLoadEvent) {
                updateProgress(fileLoadEvent.getBytesLoaded(), fileLoadEvent.getTotal());
            }
        });
    }

}
