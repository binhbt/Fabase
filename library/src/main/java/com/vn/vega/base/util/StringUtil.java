package com.vn.vega.base.util;

import android.util.Base64;

/**
 * Created by binhbt on 12/7/2016.
 */
public class StringUtil {
    // Has line break
/*    public static String getBase64(final String input) {
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }*/

    // No line break
/*    public static String getBase64(final String input) {
        return Base64.encodeToString(input.getBytes(), Base64.NO_WRAP);
    }*/

    // No line break and url safe
    public static String getBase64(final String input) {
        if (input == null) return null;
        return Base64.encodeToString(input.getBytes(), Base64.URL_SAFE);
    }
    public static String getBase64(final String input, int type) {
        return Base64.encodeToString(input.getBytes(), type);
    }
}
