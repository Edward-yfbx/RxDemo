package com.yfbx.rxdemo;

import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.net.Api;
import com.yfbx.rxdemo.net.ArraySubscriber;
import com.yfbx.rxdemo.net.EntitySubscriber;
import com.yfbx.rxdemo.net.Net;
import com.yfbx.rxdemo.net.StringSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Edward
 * Date: 2018/3/29
 * Description:
 */


public class LoginModel {


    public static void login(String user, String psd, EntitySubscriber<User> subscriber) {
        Net.create(Api.class)
                .login(user, psd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void getUser(String user, String psd, ArraySubscriber<User> subscriber) {
        Net.create(Api.class)
                .getUser(user, psd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void getJson(String user, String psd, StringSubscriber subscriber) {
        Net.create(Api.class)
                .getJson(user, psd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
