package com.example.binhbt.demo.ui;

import com.example.binhbt.demo.BuildConfig;
import com.example.binhbt.demo.net.EndPoints;
import com.vn.fa.base.FaApplication;
import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.data.cache.rxcache.RxCacheAdapterFactory;
import com.vn.fa.base.util.FaLog;

/**
 * Created by binhbt on 6/22/2016.
 */
public class DemoApplication extends FaApplication {
    public static FaApplication faApplication;
    public static RxCacheAdapterFactory cacheProviders;
    static volatile EndPoints singletonApi;
    @Override
    public void onCreate() {
        super.onCreate();
        faApplication = this;
        FaLog.init(BuildConfig.DEBUG);
    }
    public String getBaseUrl(){
        return EndPoints.API_ENDPOINT;
    }
    public static EndPoints getApi() {
        if (singletonApi == null) {
            synchronized (EndPoints.class) {
                if (singletonApi == null) {
                    if (instance == null){
                        instance = new DemoApplication();
                    }
                    singletonApi = instance.getApi(EndPoints.class);
                }
            }
        }
        return singletonApi;
    }
    public static CacheProviders getCacheProviders(){
        if (cacheProviders == null){
            cacheProviders = new RxCacheAdapterFactory();
            cacheProviders.init(faApplication.getFilesDir());
        }
        return cacheProviders;
    }

    @Override
    public boolean isLogging() {
        return true;
    }
}
