package com.yfbx.rxdemo.activity;

import com.yfbx.rxdemo.api.Api;
import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.net.Net;
import com.yfbx.rxdemo.net.result.NetResult;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author:Edward
 * Date:2018/5/7
 * Description:
 */

public class CacheNet {

    /**
     * 网络请求/缓存
     */
    private void getUser() {
        Observable.concat(getCache(), netData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {

                    }
                });

    }


    /**
     * 请求网络数据
     */
    private Observable<User> netData() {
        return Net.create(Api.class)
                .login("username", "password")
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends NetResult<User>>>() {
                    @Override
                    public Observable<? extends NetResult<User>> call(Throwable throwable) {
                        return Observable.empty();
                    }
                })
                .map(new Func1<NetResult<User>, User>() {
                    @Override
                    public User call(NetResult<User> result) {
                        return result.code == 0 ? result.data : null;
                    }
                })
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        return Observable.just(user);
                    }
                });
    }

    /**
     * 读取缓存
     */
    private Observable<User> getCache() {
        return Observable.just("prefs key")
                .map(new Func1<String, User>() {
                    @Override
                    public User call(String s) {
                        // TODO: 2017/12/15 读取缓存，并转换为User
                        return new User();
                    }
                })
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        return Observable.just(user);
                    }
                });
    }
}
