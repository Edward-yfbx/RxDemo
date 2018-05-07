package com.yfbx.rxdemo.net.subscriber;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yfbx.rxdemo.App;
import com.yfbx.rxdemo.net.result.NetResult;

import rx.Subscriber;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class NetSubscriber<T> extends Subscriber<NetResult<T>> implements DialogInterface.OnCancelListener {

    private static final String TAG = "Net";
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
    public void onCancel(DialogInterface dialog) {
        unsubscribe();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Toast.makeText(App.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(NetResult<T> result) {
        Log.i(TAG, new Gson().toJson(result));
        onSuccess(result.code, result.message, result.data);
    }

    public abstract void onSuccess(int code, String msg, T t);

}
