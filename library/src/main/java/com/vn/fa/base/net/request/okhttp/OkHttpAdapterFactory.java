package com.vn.fa.base.net.request.okhttp;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vn.fa.base.net.request.RequestType;
import com.vn.fa.base.net.request.RestEndPoints;
import com.vn.fa.net.adapter.Request;

import java.lang.reflect.Type;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import retrofit2.http.FieldMap;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by leobui on 10/25/2017.
 */

public class OkHttpAdapterFactory extends Request.Factory implements RestEndPoints {
    @Override
    public  Observable<Object> callGetApi(@Path("path") String path, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers, Type objType) {
        return createGetObservable(path, params, headers, objType);
    }

    @Override
    public  Observable<Object> callPostApi(@Path("path") String path, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers, Type objType) {
        return createPostObservable(path, params, objType);
    }

    @Override
    public Observable<Object> callPostApiWithFormUrlEncoded(@Path("path") String path, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers, Type objType) {
        return null;
    }

    private <T> T convertToObject(String json, Type type){
        try {
            T userEntity = new Gson().fromJson(json, type);
            return userEntity;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    @Override
    public  Observable<Object> callApi(RequestType type, String path, Map<String, String> params, @HeaderMap Map<String, String> headers, Type objType) {
        if (type == RequestType.GET){
            return callGetApi(path, params, headers, objType);
        }
        if (type == RequestType.POST){
            return callPostApi(path, params, headers, objType);
        }
        return null;
    }
    public <T> Observable<T> createGetObservable(String path, final Map<String, String> params, @HeaderMap Map<String, String> headers, final Type objType) {
        final String url = baseUrl+path;
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) {
                try {
                    String responseUserEntities = ApiConnection.create(url).requestSyncCall(params);
                    if (responseUserEntities != null) {
                        //Type type = new TypeToken<List<VersionApp>>() {}.getType();
                        subscriber.onNext((T)convertToObject(responseUserEntities, objType));
                        subscriber.onComplete();
                    } else {
                        subscriber.onError(new OkHttpException());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(new OkHttpException(e.getCause()));
                }
            }
        });
/*        return Observable.create(subscriber -> {
            try {
                String responseUserEntities = ApiConnection.create(url).requestSyncCall(params);
                if (responseUserEntities != null) {
                    //Type type = new TypeToken<List<VersionApp>>() {}.getType();
                    subscriber.onNext(convertToObject(responseUserEntities, objType));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new OkHttpException());
                }
            } catch (Exception e) {
                e.printStackTrace();
                subscriber.onError(new OkHttpException(e.getCause()));
            }
        });*/
    }
    public <T> Observable<T> createPostObservable(String path, final Map<String, String> params, final Type objType) {
        final String url = baseUrl+path;
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) {
                try {
                    String responseUserEntities = ApiConnection.create(url).postSync(params);
                    if (responseUserEntities != null) {
                        //Type type = new TypeToken<List<VersionApp>>() {}.getType();
                        subscriber.onNext((T)convertToObject(responseUserEntities, objType));
                        subscriber.onComplete();
                    } else {
                        subscriber.onError(new OkHttpException());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(new OkHttpException(e.getCause()));
                }
            }

        });
/*        return Observable.create(subscriber -> {
            try {
                String responseUserEntities = ApiConnection.create(url).postSync(params);
                if (responseUserEntities != null) {
                    //Type type = new TypeToken<List<VersionApp>>() {}.getType();
                    subscriber.onNext(convertToObject(responseUserEntities, objType));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new OkHttpException());
                }
            } catch (Exception e) {
                e.printStackTrace();
                subscriber.onError(new OkHttpException(e.getCause()));
            }
        });*/
    }
}
