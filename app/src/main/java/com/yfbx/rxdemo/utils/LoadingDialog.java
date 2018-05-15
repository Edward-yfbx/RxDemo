package com.yfbx.rxdemo.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.yfbx.rxdemo.R;


/**
 * Author: Edward
 * Date: 2018/4/14
 * Description:
 */


public class LoadingDialog extends Dialog {

    private ProgressBar progressBar;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        progressBar = new ProgressBar(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(progressBar);
    }
}
