package com.vn.fa.base.model;

import java.io.Serializable;

/**
 * Created by binhbt on 8/2/2016.
 */
public class VegaListObject<T> implements Serializable{
    private int code;
    private String message;
    private Data<T> data;

    public T getList(){
        if (data != null){
            return data.getList();
        }
        return null;
    }
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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

    class Data<D>{
        private int total;
        private D lists;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public D getList() {
            return lists;
        }

        public void setList(D list) {
            this.lists = list;
        }
    }
}
