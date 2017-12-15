package com.yfbx.rxdemo;

import android.app.Application;

/**
 * Date:2017/12/11
 * Author:Edward
 * Description:
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }


    public static App getApp() {
        return app;
    }


}
