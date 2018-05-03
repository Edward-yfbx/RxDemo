package com.yfbx.rxdemo.rxjava.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.Toast;

import com.yfbx.rxdemo.App;

import rx.Subscriber;


/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class NetSubscriber<T> extends Subscriber<NetResult<T>> implements DialogInterface.OnCancelListener {

    private ProgressDialog pd;

    public NetSubscriber() {
    }

    public NetSubscriber(Context context) {
        pd = new ProgressDialog(context);
        pd.setOnCancelListener(this);
    }

    @Override
    public void onStart() {
        if (pd == null) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                pd.show();
            }
        });
    }

    @Override
    public void onCompleted() {
        if (pd != null) {
            pd.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Toast.makeText(App.getApp(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(NetResult<T> result) {
        onSuccess(result.code, result.msg, result.data);
    }

    public abstract void onSuccess(int code, String msg, T t);


    @Override
    public void onCancel(DialogInterface dialog) {
        unsubscribe();
    }
}
