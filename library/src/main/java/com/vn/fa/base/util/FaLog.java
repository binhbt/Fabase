package com.vn.fa.base.util;

import timber.log.Timber;

/**
 * Created by leobui on 5/3/2017.
 */

public class FaLog {
    public static void e(String msg) {
        Timber.e(msg);
    }

    public static void v(String msg) {
        Timber.v(msg);
    }

    public static void d(String msg) {
        Timber.d(msg);
    }

    public static void i(String msg) {
        Timber.i(msg);
    }

    public static void e(String tag, String msg) {
        Timber.tag(tag).e(msg);
    }

    public static void v(String tag, String msg) {
        Timber.tag(tag).v(msg);
    }

    public static void d(String tag, String msg) {
        Timber.tag(tag).d(msg);
    }

    public static void i(String tag, String msg) {
        Timber.tag(tag).i(msg);
    }


    public static void init(boolean debug){
        if (debug) {
            Timber.plant(new Timber.DebugTree());
        } else {
        }
    }

}
