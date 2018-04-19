package com.yfbx.rxdemo.rxjava.rxbus;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private static volatile RxBus instance;
    private final Subject<Object, Object> subject;

    private RxBus() {
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 单例
     */
    public static RxBus getDefault() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public static <T> void registerEvent(Class<T> clazz, RxBusSubscriber<T> subscriber) {
        getDefault().toObservable(clazz).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    public void post(Object event) {
        subject.onNext(event);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return subject.ofType(eventType);
    }
}
