package com.yfbx.rxdemo.net.subscriber;

import android.content.Context;
import android.widget.Toast;


import com.yfbx.rxdemo.App;
import com.yfbx.rxdemo.net.result.NetResult;

import java.util.List;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public abstract class ArraySubscriber<T> extends NetSubscriber<List<T>> {

    public ArraySubscriber() {
    }

    public ArraySubscriber(Context context) {
        super(context);
    }

    @Override
    public void onNext(NetResult<List<T>> result) {
        if (result.code == 1) {
            onSuccess(result.data);
        } else {
            Toast.makeText(App.getInstance(), result.message, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void onSuccess(List<T> list);

}
