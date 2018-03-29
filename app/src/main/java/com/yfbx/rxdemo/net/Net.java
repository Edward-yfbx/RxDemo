package com.yfbx.rxdemo.net;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date:2017/12/12
 * Author:Edward
 * Description:
 */
public class Net {


    private static final String HOST = "";
    public static final int RET_SUCCESS = 200;

    /**
     * 创建API实例
     */
    public synchronized static <T> T create(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    /**
     * 创建Retrofit 实例
     */
    private static Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HOST);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(client());
        return builder.build();
    }


    /**
     * Client 拦截器
     */
    private static OkHttpClient client() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        // TODO: 2017/12/12 配置 sessionId
                        .addHeader("sessionId", "sessionId")
                        .method(request.method(), request.body())
                        .url(request.url())
                        .build();
                return chain.proceed(newRequest);
            }
        });
        return builder.build();
    }
}
