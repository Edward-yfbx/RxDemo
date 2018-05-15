package com.yfbx.rxdemo.rxbus.event;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public class ProgressEvent {

    public long total;
    public long progress;
    public int percent;

    public ProgressEvent(long total, long progress) {
        this.total = total;
        this.progress = progress;
        percent = (int) (progress * 100 / total);
    }

}
