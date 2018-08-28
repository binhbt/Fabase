package com.vn.fa.base.holder;

import android.content.Context;
import android.view.View;

import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by binhbt on 7/22/2016.
 */
public class FaViewHolder extends BinderViewHolder {
    public FaViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void sendEvent(Object event){
        EventBus.getDefault().post(event);
    }
    public Context getContext(){
        return itemView.getContext();
    }
}
