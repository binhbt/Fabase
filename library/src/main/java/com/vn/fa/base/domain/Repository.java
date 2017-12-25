package com.vn.fa.base.domain;

import com.vn.fa.base.data.cache.CacheType;
import com.vn.fa.base.data.net.request.RequestType;

import java.lang.reflect.Type;
import java.util.Map;

import io.reactivex.Observable;


/**
 * Created by leobui on 11/17/2017.
 */

public interface Repository {
    public Observable<Object> callGetApi(String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, final boolean update, CacheType cacheType);

    public Observable<Object> callPostApi(String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, final boolean update, CacheType cacheType);

    public Observable<Object> callPostApiWithFormUrlEncoded(String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, final boolean update, CacheType cacheType);

    public Observable<Object> callApi(RequestType type, String path, Map<String, String> params, Map<String, String> headers, Type objType, String dynamicKey, final boolean update, CacheType cacheType);

}
