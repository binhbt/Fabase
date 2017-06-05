package com.vn.vega.base;


import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.vn.vega.net.ApiService;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by binhbt on 6/22/2016.
 */
public abstract class VegaApplication extends MultiDexApplication{
    public static volatile VegaApplication instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initPicasso();
    }
    public  <T> T getApi(Class<T> clazz){
        return new ApiService.Builder()
                .baseUrl(getBaseUrl())
                .logging(isLogging())
                .build()
                .create(clazz);
    }

    public  boolean isLogging(){
        return false;
    }
    public  String getBaseUrl(){
        return null;
    }

    public static VegaApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    protected void initPicasso() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(getCacheDir(), "http");
        final Cache cache = new Cache(cacheDir, Integer.MAX_VALUE);
        builder.cache(cache);
        builder.build();
        okhttp3.OkHttpClient okHttp3Client = builder.build();//new okhttp3.OkHttpClient();
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttp3Client);
        Picasso picasso = new Picasso.Builder(this)
                .downloader(okHttp3Downloader).build();
        Picasso.setSingletonInstance(picasso);
        Picasso.with(this).setIndicatorsEnabled(picassoIndicator());
        Picasso.with(this).setLoggingEnabled(isLogging());
    }
    protected boolean picassoIndicator(){
        return false;
    }
}
