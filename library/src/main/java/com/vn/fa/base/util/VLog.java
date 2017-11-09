package com.vn.fa.base.util;

import android.util.Log;
import android.widget.Toast;

import com.vn.vega.base.BuildConfig;

/**
 * Created by leobui on 5/3/2017.
 */

public class VLog {
    public static void e(String tag, String msg){
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
    public static void v(String tag, String msg){
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
    public static void i(String tag, String msg){
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }
}
