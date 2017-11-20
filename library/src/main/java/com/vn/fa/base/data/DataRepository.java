package com.vn.fa.base.data;

import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.domain.Repository;
import com.vn.fa.base.net.request.RequestType;
import com.vn.fa.base.net.request.RestEndPoints;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;


/**
 * Created by leobui on 11/17/2017.
 */

public class DataRepository implements Repository{
    private RestEndPoints api;
    private CacheProviders cacheFactory;
    public static Repository init(RestEndPoints api, CacheProviders cacheFactory) {
        return new DataRepository(api, cacheFactory);
    }

    public DataRepository(RestEndPoints api, CacheProviders cacheFactory){
        this.api = api;
        this.cacheFactory = cacheFactory;
    }
    @Override
    public Observable<Object> callGetApi(String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, boolean update) {
        Observable<Object> apix = api.callGetApi(path, params, headers, objType);
        return cacheFactory.getData(apix, dynamicKey, update);

    }

    @Override
    public Observable<Object> callPostApi(String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, boolean update) {
        return api.callPostApi(path, params, headers, objType);
    }

    @Override
    public Observable<Object> callPostApiWithFormUrlEncoded(String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, boolean update) {
        return api.callPostApiWithFormUrlEncoded(path, params, headers, objType);
    }

    @Override
    public Observable<Object> callApi(RequestType type, String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, boolean update) {
        if (params == null) params = new HashMap<>();
        if (headers == null) headers = new HashMap<>();
        if (type == RequestType.GET){
            return callGetApi(path, params, headers, objType, dynamicKey, update);
        }
        if (type == RequestType.POST){
            return callPostApi(path, params, headers, objType, dynamicKey, update);
        }
        if (type == RequestType.POST_WITH_FORM_ENCODED){
            return callPostApiWithFormUrlEncoded(path, params, headers, objType, dynamicKey, update);
        }
        return null;
    }
}
