package com.yfbx.rxdemo.net.subscriber;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.Toast;

import com.yfbx.rxdemo.App;
import com.yfbx.rxdemo.net.result.NetResult;
import com.yfbx.rxdemo.utils.LoadingDialog;
import com.yfbx.rxdemo.utils.NetConfig;

import rx.Subscriber;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class NetSubscriber<T> extends Subscriber<NetResult<T>> implements DialogInterface.OnCancelListener {

    private LoadingDialog loadingView;

    public NetSubscriber() {
    }

    public NetSubscriber(Context context) {
        loadingView = new LoadingDialog(context);
        loadingView.setOnCancelListener(this);
    }

    private void showLoading() {
        if (loadingView == null) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                loadingView.show();
            }
        });
    }

    private void dismissLoading() {
        if (loadingView != null) {
            loadingView.dismiss();
        }
    }

    @Override
    public void onStart() {
        showLoading();
    }

    @Override
    public void onCompleted() {
        dismissLoading();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        unsubscribe();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        dismissLoading();
        boolean isNetAvailable = NetConfig.isNetAvailable(App.getInstance());
        String msg = isNetAvailable ? "服务器异常" : "网络连接不可用";
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}
