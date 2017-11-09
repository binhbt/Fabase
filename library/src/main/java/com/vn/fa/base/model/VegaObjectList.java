package com.vn.fa.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by binhbt on 10/1/2016.
 */
public class VegaObjectList<T> implements Serializable {
    private int code;
    private String message;
    private Data<T> data;

    public  List<T> getList(){
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
        private List<D> lists;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public  List<D> getList() {
            return lists;
        }

        public void setList( List<D> list) {
            this.lists = list;
        }
    }
}