package com.vn.vega.base.net.request.okhttp;

/**
 * Created by leobui on 10/26/2017.
 */

public class OkHttpException extends Exception {

    public OkHttpException() {
        super();
    }

    public OkHttpException(final String message) {
        super(message);
    }

    public OkHttpException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OkHttpException(final Throwable cause) {
        super(cause);
    }
}
