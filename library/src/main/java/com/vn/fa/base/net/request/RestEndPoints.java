package com.vn.fa.base.net.request;

import java.lang.reflect.Type;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by leobui on 10/27/2017.
 */

public interface RestEndPoints {

    @GET("{path}")
    public  Observable<Object> callGetApi(@Path("path") String path, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers, @Header("type-ignore") Type objType);

    @POST("{path}")
    public Observable<Object> callPostApi(@Path("path") String path, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers, @Header("type-ignore") Type objType);

    @FormUrlEncoded
    @POST("{path}")
    public  Observable<Object> callPostApiWithFormUrlEncoded(@Path("path") String path, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers, @Header("type-ignore") Type objType);

    public Observable<Object> callApi(RequestType type, String path, Map<String, String> params, Map<String, String> headers, Type objType);

}
