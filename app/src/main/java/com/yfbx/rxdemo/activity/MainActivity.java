package com.yfbx.rxdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yfbx.rxdemo.R;
import com.yfbx.rxdemo.api.Api;
import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.net.Net;
import com.yfbx.rxdemo.net.subscriber.NetSubscriber;
import com.yfbx.rxdemo.net.NetTrans;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();

    }

    /**
     * 网络请求
     */
    private void login() {
        Net.create(Api.class)
                .login("admin", "admin")
                .compose(new NetTrans<User>())
                .subscribe(new NetSubscriber<User>(this) {
                    @Override
                    public void onSuccess(int code, String msg, User user) {

                    }

                });
    }

    private void getList() {
        Net.create(Api.class)
                .getList("", "")
                .compose(new NetTrans<List<User>>())
                .subscribe(new NetSubscriber<List<User>>() {
                    @Override
                    public void onSuccess(int code, String msg, List<User> users) {

                    }
                });
    }

}
