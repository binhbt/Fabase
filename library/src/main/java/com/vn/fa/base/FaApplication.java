package com.vn.fa.base;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.vn.fa.net.ApiService;

/**
 * Created by binhbt on 6/22/2016.
 */
public abstract class FaApplication extends MultiDexApplication {
    public static volatile FaApplication instance = null;
    // add comment
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //initImageLoader();


    }

//    protected void initImageLoader(){
//        FALoader.initialize(this);
//    }
    public static FaApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public <T> T getApi(Class<T> clazz) {
        return new ApiService.Builder()
                .baseUrl(getBaseUrl())
                .logging(isLogging())
                .build()
                .create(clazz);
    }

    public boolean isLogging() {
        return false;
    }

    public String getBaseUrl() {
        return null;
    }
}
