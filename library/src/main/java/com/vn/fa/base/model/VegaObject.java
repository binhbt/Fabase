package com.vn.fa.base.model;

import java.io.Serializable;

/**
 * Created by binhbt on 8/2/2016.
 */
public class VegaObject<T> implements Serializable{
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
