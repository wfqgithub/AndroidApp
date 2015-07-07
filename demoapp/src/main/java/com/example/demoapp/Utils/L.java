package com.example.demoapp.Utils;

/**
 * Created by Administrator on 2015/7/3.
 */

import android.util.Log;

import com.example.demoapp.app.Constans;

/**
 * Log统一管理类
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final String TAG = "Log";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (Constans.isDebugMode)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (Constans.isDebugMode)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (Constans.isDebugMode)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (Constans.isDebugMode)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (Constans.isDebugMode)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (Constans.isDebugMode)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (Constans.isDebugMode)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (Constans.isDebugMode)
            Log.i(tag, msg);
    }
}
