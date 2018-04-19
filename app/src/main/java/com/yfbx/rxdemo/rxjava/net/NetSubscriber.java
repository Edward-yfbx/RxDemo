package com.yfbx.rxdemo.rxjava.net;

import android.util.Log;

import com.google.gson.Gson;

import rx.Observer;


/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class NetSubscriber<T> implements Observer<NetResult<T>> {

    private static final String TAG = "NET";

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFinish(false, e.getMessage());
    }

    @Override
    public void onNext(NetResult<T> result) {
        Log.i(TAG, new Gson().toJson(result));
        onSuccess(result);
        onFinish(true, result.msg);
    }


    public abstract void onSuccess(NetResult<T> result);

    public void onFinish(boolean isError, String msg) {

    }
}
