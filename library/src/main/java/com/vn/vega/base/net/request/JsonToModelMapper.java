package com.vn.vega.base.net.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by leobui on 10/30/2017.
 */

public class JsonToModelMapper {
    public static Object transform(final Object json, final Type typeOfT){
        Gson gson = new Gson();
        String strJson = gson.toJson(json);
        if (typeOfT != null){
            try {
                Object obj = new Gson().fromJson(strJson, typeOfT);
                return obj;
            } catch (JsonSyntaxException jsonException) {
                jsonException.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
