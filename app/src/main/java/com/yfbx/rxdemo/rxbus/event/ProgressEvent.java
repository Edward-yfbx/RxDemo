package com.yfbx.rxdemo.rxbus.event;

import java.text.DecimalFormat;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public class ProgressEvent {

    public double total;
    public double progress;
    public int percent;

    public ProgressEvent(double total, double progress) {
        this.total = total;
        this.progress = progress;
        getPercent();
    }

    private void getPercent() {
        double data = progress / total;
        String format = new DecimalFormat("0.00").format(data);
        percent = (int) (Double.valueOf(format) * 100);
    }

}
