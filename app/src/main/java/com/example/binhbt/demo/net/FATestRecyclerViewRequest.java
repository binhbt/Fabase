package com.example.binhbt.demo.net;

import com.example.binhbt.demo.model.VersionApp;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.model.VegaObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by leobui on 11/3/2017.
 */

public class FATestRecyclerViewRequest extends BaseRequest{
    @Override
    protected String getPath() {
        return "test/test_list.json";
    }

    @Override
    protected Type getDataType() {
        Type type = new TypeToken<VegaObject<List<VersionApp>>>() {}.getType();
        return type;
    }
    @Override
    public Convert getConvert() {
        return new Convert<VegaObject<List<VersionApp>>, List<VersionApp>>() {

            @Override
            public List<VersionApp> apply(VegaObject<List<VersionApp>> o) {
                return o.getData();
            }
        };
    }

}
