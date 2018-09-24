package com.example.binhbt.demo.net;

import com.example.binhbt.demo.model.VersionApp;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.net.request.RequestType;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by leobui on 10/27/2017.
 */

public class HomeRequest extends BaseRequest {
    @Override
    protected String getPath() {
        return "other_app.json";
    }

    @Override
    protected Type getDataType() {
        Type type = new TypeToken<List<VersionApp>>() {}.getType();
        return type;
    }



    @Override
    public RequestType getType() {
        return RequestType.GET;
    }


}
