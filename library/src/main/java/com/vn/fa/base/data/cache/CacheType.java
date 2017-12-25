package com.vn.fa.base.data.cache;

/**
 * Created by leobui on 12/22/2017.
 */

public enum CacheType {
    CACHE_FIRST,//Cache >> Net
    NET_FIRST,//Net >> Cache: request api if die use cache
    CACHE_NET,
    FLUSH_CACHE,//evict cache
    NONE// not cache
}
