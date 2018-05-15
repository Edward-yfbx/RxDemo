package com.yfbx.rxdemo.net.result;

import com.yfbx.rxdemo.rxbus.RxBus;
import com.yfbx.rxdemo.rxbus.event.ProgressEvent;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Author:Edward
 * Date:2018/5/15
 * Description:
 */

public class FileRequestBody extends RequestBody {

    private RequestBody fileBody;

    public FileRequestBody(File file) {
        this.fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//        this.fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    @Override
    public MediaType contentType() {
        return fileBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return fileBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountingSink countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        fileBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            RxBus.getDefault().post(new ProgressEvent(contentLength(), bytesWritten));
        }
    }

}
