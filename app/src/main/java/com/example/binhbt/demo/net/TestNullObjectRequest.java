package com.example.binhbt.demo.net;

import com.example.binhbt.demo.model.User;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.net.request.RequestType;

import java.lang.reflect.Type;

/**
 * Created by leobui on 12/19/2017.
 */

public class TestNullObjectRequest extends BaseRequest {
    @Override
    protected String getPath() {
        return "test/user_null.json";
    }

    @Override
    protected Type getDataType() {
        Type type = new TypeToken<User>() {}.getType();
        return type;
    }



    @Override
    public RequestType getType() {
        return RequestType.GET;
    }


}