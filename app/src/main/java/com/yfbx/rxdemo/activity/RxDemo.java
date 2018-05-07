package com.yfbx.rxdemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yfbx.rxdemo.R;
import com.yfbx.rxdemo.bean.User;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Date:2017/12/15
 * Author:Edward
 * Description:
 */

public class RxDemo extends Activity {

    private TextView text;
    private Button btn;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        btn = findViewById(R.id.btn);
        text = findViewById(R.id.info);
    }


    /**
     * 观察按钮点击事件
     */
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(final Subscriber<? super String> subscriber) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subscriber.onNext(text.getText().toString());
                }
            });

        }
    });


    private void test2() {
        //切换到主线程
        Subscription subscribe = observable.observeOn(AndroidSchedulers.mainThread())
                //在主线程进行操作
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        pd.show();
                    }
                })
                //切换到IO线程
                .observeOn(Schedulers.io())
                //在IO线程对数据进行操作
                .map(new Func1<String, User>() {
                    @Override
                    public User call(String s) {
                        // TODO: 2017/12/15 数据计算操作，数据查询操作...

                        return new User();
                    }

                })
                //筛选，filter只会通过那些满足条件的item
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                //防抖动,根据item被发射的时间来进行过滤。每次在一个item被发射后，debounce 会等待一段指定长度的时间，然后才去发射下一个item。
                //(有防止连续点击的意思？？)
                .debounce(1000, TimeUnit.MILLISECONDS)
                //切换到UI线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        // TODO: 2017/12/15 数据操作结果
                    }
                });

        //解除订阅（生命周期处理，在onStop()或在onDestroy()中调用）
        subscribe.unsubscribe();


    }

    /**
     * 绑定两个事件
     */
    private void test3() {
        observable.mergeWith(observable);
        observable.ambWith(observable);
        observable.concatWith(observable);
        Observable.concat(observable, observable);
        Observable.amb(observable, observable);
    }

    private void test4() {
//        just: 获取输入数据, 直接分发, 更加简洁, 省略其他回调.
//        from: 获取输入数组, 转变单个元素分发.
//        map: 映射, 对输入数据进行转换, 如大写.
//        flatMap: 增大, 本意就是增肥, 把输入数组映射多个值, 依次分发.
//        reduce: 简化, 正好相反, 把多个数组的值, 组合成一个数据.

    }

}
