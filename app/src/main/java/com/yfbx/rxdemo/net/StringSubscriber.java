package com.yfbx.rxdemo.net;

import android.widget.Toast;

import com.yfbx.rxdemo.App;

import rx.Observer;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class StringSubscriber implements Observer<String> {

    private boolean isSuccess;
    private String msg;

    @Override
    public void onCompleted() {
        onCompleted(isSuccess, msg);
    }

    @Override
    public void onError(Throwable e) {
        isSuccess = false;
        boolean netAvailable = NetConfig.isNetAvailable(App.getApp());
        msg = netAvailable ? "服务器异常" : "网络异常";
    }

    @Override
    public void onNext(String ret) {
        isSuccess = true;
        msg = ret;
        onSuccess(ret);
    }

    public abstract void onSuccess(String result);


    public void onCompleted(boolean isSuccess, String msg) {
        if (!isSuccess) {
            Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
