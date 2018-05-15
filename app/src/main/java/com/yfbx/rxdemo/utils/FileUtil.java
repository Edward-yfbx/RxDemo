package com.yfbx.rxdemo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author:Edward
 * Date:2018/5/15
 * Description:
 */

public class FileUtil {

    /**
     * 保存文件
     */
    public static void saveFile(File file, InputStream is) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[2048];
            int len;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
