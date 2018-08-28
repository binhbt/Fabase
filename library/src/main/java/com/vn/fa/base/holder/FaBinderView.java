package com.vn.fa.base.holder;

import com.vn.fa.adapter.multipleviewtype.FaDataBinder;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by binhbt on 12/7/2016.
 */
public abstract class FaBinderView<T> extends FaDataBinder<T> {
    public FaBinderView(T data){
        super(data);
    }
    protected void sendEvent(Object event){
        EventBus.getDefault().post(event);
    }


}
