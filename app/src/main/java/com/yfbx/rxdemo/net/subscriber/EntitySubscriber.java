package com.yfbx.rxdemo.net.subscriber;

import android.content.Context;
import android.widget.Toast;

import com.yfbx.rxdemo.App;
import com.yfbx.rxdemo.net.result.NetResult;


/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class EntitySubscriber<T> extends NetSubscriber<T> {

    public EntitySubscriber() {
    }

    public EntitySubscriber(Context context) {
        super(context);
    }

    @Override
    public void onNext(NetResult<T> result) {
        if (result.code == 1) {
            onSuccess(result.data);
        } else {
            Toast.makeText(App.getInstance(), result.message, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void onSuccess(T t);


}
