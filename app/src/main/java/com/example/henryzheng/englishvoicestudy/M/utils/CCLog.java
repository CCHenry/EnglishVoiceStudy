package com.example.henryzheng.englishvoicestudy.M.utils;

import android.util.Log;

/**
 * Created by henryzheng on 2017/2/15.
 */

public class CCLog {
    public static void d(String content) {
        Log.d(ConfigUtils.LOG_TAG, content);
    }

    public static void e(String content) {
        Log.e(ConfigUtils.LOG_TAG, content);
    }

    public static void i(String content) {
        Log.i(ConfigUtils.LOG_TAG, content);
    }

    public static void w(String content) {
        Log.w(ConfigUtils.LOG_TAG, content);
    }

    public static void v(String content) {
        Log.v(ConfigUtils.LOG_TAG, content);
    }
}
