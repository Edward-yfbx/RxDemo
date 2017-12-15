package com.yfbx.rxdemo.net;

import android.content.Context;
import android.widget.Toast;

import com.yfbx.rxdemo.App;
import com.yfbx.rxdemo.bean.NetResult;

import rx.Observer;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class MySubscriber<T> implements Observer<T> {

    private Context context = App.getApp();
    private boolean isSuccess;
    private String msg;

    @Override
    public void onCompleted() {
        onCompleted(isSuccess, msg);
    }

    @Override
    public void onError(Throwable e) {
        isSuccess = false;
        if (NetConfig.isNetAvailable(context)) {
            Toast.makeText(context, "服务器异常!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "网络不可用!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNext(T t) {
        isSuccess = true;

        if (t instanceof NetResult) {
            NetResult result = (NetResult) t;
            this.msg = result.msg;

            if (result.code == 0) {
                onSuccess(t);
                return;
            }

            isSuccess = false;

            if (result.code == 200) {
                sessionFail();
            }
        }
    }

    /**
     * Session过期
     */
    private void sessionFail() {
        // TODO: 2017/12/15 跳转到登陆页
    }

    public abstract void onCompleted(boolean isSuccess, String msg);

    public abstract void onSuccess(T t);


}
