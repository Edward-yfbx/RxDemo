package com.yfbx.rxdemo.activity;

import com.yfbx.rxdemo.api.Api;
import com.yfbx.rxdemo.net.Net;
import com.yfbx.rxdemo.net.result.FileRequestBody;
import com.yfbx.rxdemo.net.subscriber.FileSubscriber;
import com.yfbx.rxdemo.utils.FileUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author:Edward
 * Date:2018/5/7
 * Description:
 */

public class LoadModel {

    /**
     * 下载文件
     */
    public void downloadFile(String url, final File targetFile, FileSubscriber<ResponseBody> subscriber) {
        Net.createLoader(Api.class)
                .download(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        FileUtil.saveFile(targetFile, body.byteStream());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 上传文件
     */
    public void upload(String url, File file, final Subscriber<ResponseBody> subscriber) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        Net.createLoader(Api.class)
                .upload(url, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 上传文件
     */
    public void uploadFile(String url, File file, FileSubscriber<ResponseBody> subscriber) {
        FileRequestBody fileBody = new FileRequestBody(file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        Net.create(Api.class)
                .uploadFile(url, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
