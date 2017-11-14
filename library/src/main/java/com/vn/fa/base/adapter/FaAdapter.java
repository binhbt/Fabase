package com.vn.fa.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.vn.fa.adapter.multipleviewtype.VegaBindAdapter;
import com.vn.fa.base.holder.OnItemClickListener;

/**
 * Created by leobui on 11/9/2017.
 */

public class FaAdapter extends VegaBindAdapter{
    private OnItemClickListener onItemClickListener;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        super.onBindViewHolder(viewHolder, position);
        if (onItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null){
                        onItemClickListener.onClick(view, position);
                    }
                }
            });
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
