package com.vn.fa.base.data.cache.rxcache;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by leobui on 11/20/2017.
 */

public interface RxCacheProviders {
    @LifeCache(duration = 2, timeUnit = TimeUnit.DAYS)
    Observable<Reply<Object>> getData(Observable<Object> observable, DynamicKey dynamicKey, EvictProvider evictProvider);

}
