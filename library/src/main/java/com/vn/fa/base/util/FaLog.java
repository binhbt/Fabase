package com.vn.fa.base.util;

import timber.log.Timber;

/**
 * Created by leobui on 5/3/2017.
 */

public class FaLog {
    public static void e(String tag, String msg) {
        Timber.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        Timber.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        Timber.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        Timber.i(tag, msg);
    }

    public static void init(){

    }

}
