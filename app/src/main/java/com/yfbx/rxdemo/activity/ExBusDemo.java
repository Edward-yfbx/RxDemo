package com.yfbx.rxdemo.activity;

import com.yfbx.rxdemo.event.ExampleEvent;
import com.yfbx.rxdemo.rxjava.rxbus.RxBus;
import com.yfbx.rxdemo.rxjava.rxbus.RxBusSubscriber;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public class ExBusDemo {

    private void postEvent() {
        RxBus.getDefault().post(new ExampleEvent());
    }


    private void onEvent() {
        RxBus.registerEvent(ExampleEvent.class, new RxBusSubscriber<ExampleEvent>() {
            @Override
            protected void onEvent(ExampleEvent exampleEvent) {

            }
        });
    }
}
