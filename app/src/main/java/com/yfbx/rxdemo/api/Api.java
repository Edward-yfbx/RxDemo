package com.yfbx.rxdemo.api;

import com.yfbx.rxdemo.rxjava.net.NetResult;
import com.yfbx.rxdemo.bean.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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

}
