package com.yfbx.rxdemo.net.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public class NetInterceptor implements Interceptor {

    private static final String TAG = "NET";
    private Headers headers;

    public NetInterceptor() {
    }

    public NetInterceptor(Headers headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.method(request.method(), request.body());
        builder.url(request.url());
        if (headers != null) {
            builder.headers(headers);
        }
        Log.i(TAG, "URL:" + request.url());
        return chain.proceed(builder.build());
    }
}
