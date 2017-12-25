package com.vn.fa.base.data.cache.rxcache;

import com.vn.fa.base.data.cache.CacheFactory;
import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.data.net.request.JsonToModelMapper;

import java.io.File;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by leobui on 11/17/2017.
 */

public class RxCacheAdapterFactory extends CacheFactory implements CacheProviders{
    private RxCacheProviders cacheProviders;
    @Override
    public Observable<Object> getData(Observable<Object> observable, final Type objType, String dynamicKey, boolean update) {
        return cacheProviders.getData(observable, new DynamicKey(dynamicKey), new EvictDynamicKey(update)).map(new Function<Reply<Object>, Object>() {
            @Override
            public Object apply(@NonNull Reply<Object> objectReply) throws Exception {
                return JsonToModelMapper.transform(objectReply.getData(), objType);
            }
        });
    }
    @Override
    public void init(File cacheDir) {
        cacheProviders = new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())
                .using(RxCacheProviders.class);
    }

}
