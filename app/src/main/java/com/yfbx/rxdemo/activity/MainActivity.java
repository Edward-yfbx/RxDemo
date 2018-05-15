package com.yfbx.rxdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yfbx.rxdemo.R;
import com.yfbx.rxdemo.api.Api;
import com.yfbx.rxdemo.bean.User;
import com.yfbx.rxdemo.net.Net;
import com.yfbx.rxdemo.net.Trans;
import com.yfbx.rxdemo.net.subscriber.ArraySubscriber;
import com.yfbx.rxdemo.net.subscriber.EntitySubscriber;

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
                .compose(new Trans<User>())
                .subscribe(new EntitySubscriber<User>(this) {
                    @Override
                    public void onSuccess(User user) {

                    }
                });
    }

    private void getList() {
        Net.create(Api.class)
                .getList("", "")
                .compose(new Trans<List<User>>())
                .subscribe(new ArraySubscriber<User>() {
                    @Override
                    public void onSuccess(List<User> list) {

                    }
                });
    }

}
