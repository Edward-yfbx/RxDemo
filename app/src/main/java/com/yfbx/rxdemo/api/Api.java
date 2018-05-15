package com.yfbx.rxdemo.api;

import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.net.result.NetResult;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Author:Edward
 * Date:2018/4/23
 * Description:
 */

public interface Api {

    @FormUrlEncoded
    @POST("JHGZ/app/login.do")
    Observable<NetResult<User>> login(@Field("account") String account, @Field("password") String password);

    @FormUrlEncoded
    @POST("JHGZ/app/login.do")
    Observable<NetResult<List<User>>> getList(@Field("account") String account, @Field("password") String password);

    /**
     * 下载
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

    /**
     * 上传文件
     */
    @POST
    Observable<ResponseBody> upload(@Url String url, @Body RequestBody requestBody);

    /**
     * 上传文件
     */
    @POST
    Observable<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part part);


}
