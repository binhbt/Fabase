package com.vn.vega.base.util;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by binhbt on 8/17/2016.
 */
public class LogUtil {
    public static String getExceptionStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
    public static void logDeviceType(Context ctx){
        float density = ctx.getResources().getDisplayMetrics().density;
        // return 0.75 if it's LDPI
        // return 1.0 if it's MDPI
        // return 1.5 if it's HDPI
        // return 2.0 if it's XHDPI
        // return 3.0 if it's XXHDPI
        // return 4.0 if it's XXXHDPI
        VLog.e("density", density+"");
        if (density == 0.75f){
            VLog.e("Screen type", "LDPI");
        }
        if (density == 1.0f){
            VLog.e("Screen type", "HDPI");
        }
        if (density == 1.5f){
            VLog.e("Screen type", "LDPI");
        }
        if (density == 2.0f){
            VLog.e("Screen type", "XHDPI");
        }
        if (density == 3.0f){
            VLog.e("Screen type", "XXHDPI");
        }
        if (density == 4.0f){
            VLog.e("Screen type", "XXXHDPI");
        }
    }
}
