package com.yfbx.rxdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.yfbx.rxdemo.R;
import com.yfbx.rxdemo.api.Api;
import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.rxjava.net.Net;
import com.yfbx.rxdemo.rxjava.net.NetResult;
import com.yfbx.rxdemo.rxjava.net.NetSubscriber;
import com.yfbx.rxdemo.rxjava.net.NetTrans;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button btn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();

    }

    /**
     * 网络请求
     */
    private void login() {
        Net.create(Api.class)
                .login("admin", "admin")
                .compose(new NetTrans<User>())
                .subscribe(new NetSubscriber<User>(this) {
                    @Override
                    public void onSuccess(int code, String msg, User user) {

                    }

                });
    }

    private void getList() {
        Net.create(Api.class)
                .getList("", "")
                .compose(new NetTrans<List<User>>())
                .subscribe(new NetSubscriber<List<User>>() {
                    @Override
                    public void onSuccess(int code, String msg, List<User> users) {

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
