package com.yfbx.rxdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yfbx.rxdemo.LoginModel;
import com.yfbx.rxdemo.R;
import com.yfbx.rxdemo.bean.NetResult;
import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.net.Api;
import com.yfbx.rxdemo.net.ArraySubscriber;
import com.yfbx.rxdemo.net.EntitySubscriber;
import com.yfbx.rxdemo.net.Net;
import com.yfbx.rxdemo.net.StringSubscriber;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 网络请求
     */
    private void testLogin() {
        LoginModel.login("", "", new EntitySubscriber<User>() {
            @Override
            public void onSuccess(User user) {

            }
        });

        LoginModel.getUser("", "", new ArraySubscriber<User>() {
            @Override
            public void onSuccess(List<User> list) {

            }
        });

        LoginModel.getJson("", "", new StringSubscriber() {
            @Override
            public void onSuccess(String result) {

            }
        });

    }

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
