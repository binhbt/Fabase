package com.vn.fa.base.holder;

import android.view.View;

import com.vn.vega.adapter.multipleviewtype.BinderViewHolder;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by binhbt on 7/22/2016.
 */
public class VegaViewHolder extends BinderViewHolder {
    public VegaViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void sendEvent(Object event){
        EventBus.getDefault().post(event);
    }
}
