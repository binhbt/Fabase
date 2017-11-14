package com.vn.fa.base.net.request.retrofit;

import com.vn.fa.base.net.request.JsonToModelMapper;
import com.vn.fa.base.net.request.RequestType;
import com.vn.fa.base.net.request.RestEndPoints;
import com.vn.fa.net.ApiService;
import com.vn.fa.net.adapter.Request;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by leobui on 10/27/2017.
 */

public class RetrofitAdapterFactory extends Request.Factory implements RestEndPoints {
    private RestEndPoints restEndPoints;
    public void init(){
        restEndPoints = new ApiService.Builder()
                .baseUrl(baseUrl)
                .logging(isLogging)
                .build()
                .create(RestEndPoints.class);
    }
    @Override
    public  Observable<Object> callApi(RequestType type, String path, Map<String, String> params, @HeaderMap Map<String, String> headers, final Type objType) {
        if (params == null) params = new HashMap<>();
        if (headers == null) headers = new HashMap<>();
        if (type == RequestType.GET){
            return callGetApi(path, params, headers, objType);
        }
        if (type == RequestType.POST){
            return callPostApi(path, params, headers, objType);
        }
        return null;
    }

    @Override
    public  Observable<Object> callGetApi(@Path("path") String path, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers, final Type objType) {
        return restEndPoints.callGetApi(path, params, headers, objType).map(new Func1<Object, Object>() {
            @Override
            public Object call(Object o) {
                return JsonToModelMapper.transform(o, objType);
            }
        });
    }

    @Override
    public  Observable<Object> callPostApi(@Path("path") String path, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers, final Type objType) {
        return restEndPoints.callPostApi(path, params, headers, objType).map(new Func1<Object, Object>() {
            @Override
            public Object call(Object o) {
                return JsonToModelMapper.transform(o, objType);
            }
        });
    }

    @Override
    public  Observable<Object> callPostApiWithFormUrlEncoded(@Path("path") String path, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers, final Type objType) {
        return restEndPoints.callPostApiWithFormUrlEncoded(path, params, headers, objType).map(new Func1<Object, Object>() {
            @Override
            public Object call(Object o) {
                return JsonToModelMapper.transform(o, objType);
            }
        });
    }
}
