package com.yfbx.rxdemo.api;

import com.yfbx.rxdemo.net.result.NetResult;
import com.yfbx.rxdemo.bean.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Date:2017/12/12
 * Author:Edward
 * Description:
 */

public interface Api {

    @FormUrlEncoded
    @POST("hndl/mobile/login/info/verification.action")
    Observable<NetResult<User>> login(@Field("userAccount") String username, @Field("userPassword") String psd);

    @FormUrlEncoded
    @POST("hndl/mobile/login/info/verification.action")
    Observable<NetResult<List<User>>> getList(@Field("userAccount") String username, @Field("userPassword") String psd);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

}
