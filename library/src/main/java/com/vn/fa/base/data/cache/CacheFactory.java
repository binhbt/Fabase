package com.vn.fa.base.data.cache;

import java.io.File;

/**
 * Created by leobui on 11/17/2017.
 */

public abstract class CacheFactory {
    protected  void  init(File cacheDir) {
        //Do nothing
        //Will exec after create
        //Overrite it if you need attach your code
    }

    public <T> T create(File cacheDir, final Class<T> clazz) {
        init(cacheDir);
        return (T) this;
    }
}
