package com.yfbx.rxdemo.net;

import android.widget.Toast;

import com.yfbx.rxdemo.App;
import com.yfbx.rxdemo.bean.NetResult;

import rx.Observer;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class EntitySubscriber<T> implements Observer<NetResult<T>> {

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
    public void onNext(NetResult<T> result) {
        isSuccess = true;
        this.msg = result.msg;
        if (result.code == Net.RET_SUCCESS) {
            onSuccess(result.data);
        } else {
            Toast.makeText(App.getApp(), result.msg, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void onSuccess(T t);

    public void onCompleted(boolean isSuccess, String msg) {
        if (!isSuccess) {
            Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
        }
    }


}
