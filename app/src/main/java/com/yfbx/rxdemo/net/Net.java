package com.yfbx.rxdemo.net;


import com.yfbx.rxdemo.net.interceptor.NetInterceptor;
import com.yfbx.rxdemo.net.interceptor.ProgressInterceptor;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Date:2017/12/12
 * Author:Edward
 * Description:
 */
public class Net {

    private static final String TAG = "Net";
    private static final String HOST = "";


    /**
     * 创建API实例
     */
    public synchronized static <T> T create(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    public synchronized static <T> T createLoader(Class<T> clazz) {
        return getLoader().create(clazz);
    }

    /**
     * 创建Retrofit 实例
     */
    private static Retrofit getRetrofit() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //加入请求头
        client.addInterceptor(new NetInterceptor(getHeaders()));
//        client.addInterceptor(new NetInterceptor());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HOST);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(client.build());
        return builder.build();
    }

    /**
     * 请求头
     */
    private static Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder();
        builder.add("key", "value");
        return builder.build();
    }


    private static Retrofit getLoader() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new ProgressInterceptor());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HOST);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(client.build());
        return builder.build();
    }

}
