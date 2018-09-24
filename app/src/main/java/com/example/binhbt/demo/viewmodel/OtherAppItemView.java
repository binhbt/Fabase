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
 * Created by binhbt on 3/7/2017.
 */
public class OtherAppItemView  extends FaBinderView<VersionApp> {
    public OtherAppItemView(VersionApp data) {
        super(data);
    }


    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        PhotoViewHolder holder1 = (PhotoViewHolder) holder;
        if (data.getResId() ==null) {
            Picasso.with(holder1.mImageView.getContext()).load(data.getThumb()).into(holder1.mImageView);
        }else{
            holder1.mImageView.setImageDrawable(data.getResId());
        }
        holder1.title.setText(data.getTitle());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                List<FaBinderView>  binderList= (List<FaBinderView>)(List)((FaAdapter)getAdapter()).getBinderList();
//                VersionApp versionApp = (VersionApp)binderList.get(1).getData();
            }
        });
    }

    private boolean isCreate = true;

    @Override
    public BinderViewHolder newHolder(View parent) {
        return new PhotoViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_other_app;
    }

    public static class PhotoViewHolder extends FaViewHolder {
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
