package com.yfbx.rxdemo.rxbus.event;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public class FileLoadEvent {

    private long total;
    private long bytesLoaded;

    public FileLoadEvent(long total, long bytesLoaded) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
    }

    public long getBytesLoaded() {
        return bytesLoaded;
    }

    public long getTotal() {
        return total;
    }


}
