package com.yfbx.rxdemo.net.result;


import com.yfbx.rxdemo.rxbus.event.ProgressEvent;
import com.yfbx.rxdemo.rxbus.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Author:Edward
 * Date:2018/5/4
 * Description:
 */

public class FileResponseBody extends ResponseBody {

    private ResponseBody responseBody;

    private BufferedSource bufferedSource;

    public FileResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReaded = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                RxBus.getDefault().post(new ProgressEvent(contentLength(), bytesReaded));
                return bytesRead;
            }
        };
    }
}
