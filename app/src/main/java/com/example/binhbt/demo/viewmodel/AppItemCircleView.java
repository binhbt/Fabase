package com.example.binhbt.demo.viewmodel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.model.VersionApp;
import com.squareup.picasso.Picasso;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.holder.FaBinderView;
import com.vn.fa.base.holder.FaViewHolder;

import butterknife.BindView;

/**
 * Created by leobui on 12/5/2017.
 */

public class AppItemCircleView extends FaBinderView<VersionApp> {
    public AppItemCircleView(VersionApp data) {
        super(data);
    }

    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        final AppItemCircleView.PhotoViewHolder holder1 = (AppItemCircleView.PhotoViewHolder) holder;
        if (data.getResId() ==null) {
            Picasso.with(holder1.title.getContext()).load(data.getThumb()).into(holder1.mImageView);
        }else{
            //holder1.mImageView.setImageDrawable(data.getResId());
        }
        holder1.title.setText(data.getTitle());

    }

    private boolean isCreate = true;

    @Override
    public BinderViewHolder newHolder(View parent) {
        return new AppItemCircleView.PhotoViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_other_app;
    }

    public class PhotoViewHolder extends FaViewHolder {
        @BindView(R.id.thumb)
        ImageView mImageView;
        @BindView(R.id.tittle)
        TextView title;
        @BindView(R.id.item)
        public View item;

        public PhotoViewHolder(View view) {
            super(view);
        }
    }
}
