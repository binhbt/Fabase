package com.vn.vega.base.service;

import android.app.Service;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by leobui on 10/25/2017.
 */

public abstract class VegaService extends Service {

    public void sendEvent(Object message){
        if (message == null) throw new IllegalArgumentException("Object message can not be null");
        EventBus.getDefault().post(message);
    }
    // This method will be called when a SomeOtherEvent is posted
    @Subscribe()
    public void receiveEvent(Object event) {
        handleEvent(event);
    }
    protected void handleEvent(Object event){
        //TODO Handle message reived here. Overite it
    }
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
