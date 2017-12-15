package com.yfbx.rxdemo.net;

import com.yfbx.rxdemo.bean.NetResult;
import com.yfbx.rxdemo.bean.User;

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
    @POST("mobile/login/user/login.action")
    Observable<NetResult<User>> login(@Field("username") String username, @Field("password") String psd);
}
