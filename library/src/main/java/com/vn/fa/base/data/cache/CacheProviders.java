package com.vn.fa.base.data.cache;


import io.reactivex.Observable;

/**
 * Created by leobui on 11/16/2017.
 */

public interface CacheProviders {
    Observable<Object> getData(Observable<Object> observable, String dynamicKey, boolean update);
}


