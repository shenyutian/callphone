package com.syt.cellphone.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author：syt Date: 2019-12-17
 * 作用: 测试java
 */
public class FileUtil {

    private static final String TAG = "File";

    /**
     * 设备获取设备唯一id
     * @param context 上下文
     * @return 唯一id
     */
    public static String getStrial(Context context) {
        String strial = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        File file = new File("sdcard/weixt/sequenceId.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                OutputStream outputStream = new FileOutputStream(file);
                //装换大写
                char[] outStrial = new char[strial.length()];
                for (int i = 0; i < strial.length(); i++) {
                    int k = strial.charAt(i);
                    if (k >= 97 && k <= 122) {
                        k -= 32;
                    }
                    outStrial[i] = (char) k;
                }
                strial = new String(outStrial);
                outputStream.write(strial.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "写入id = " + strial);
        } else {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[16];
                fileInputStream.read(bytes);
                strial = new String(bytes);
                Log.d(TAG, "id = " + strial);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strial;
    }
}
