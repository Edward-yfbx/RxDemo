package com.yfbx.rxdemo.activity;

import android.os.Environment;

import com.yfbx.rxdemo.api.Api;
import com.yfbx.rxdemo.net.subscriber.FileSubscriber;
import com.yfbx.rxdemo.net.Net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author:Edward
 * Date:2018/5/7
 * Description:
 */

public class Download {

    /**
     * 下载文件
     */
    public void downloadAPK(String url) {
        Net.createLoader(Api.class)
                .download(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        saveFile(body.byteStream());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FileSubscriber<ResponseBody>() {
                    @Override
                    public void updateProgress(long progress, long total) {
                        // TODO: 2018/5/7 更新进度
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        // TODO: 2018/5/7 下载完成
                    }
                });
    }

    /**
     * 保存文件
     */
    private void saveFile(InputStream is) {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String APK = "EP_Manager.apk";
        try {
            File file = new File(root, APK);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[2048];
            int len;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
