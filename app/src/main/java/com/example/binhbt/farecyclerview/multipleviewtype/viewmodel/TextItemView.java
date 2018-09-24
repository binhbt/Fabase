package com.example.binhbt.farecyclerview.multipleviewtype.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.example.binhbt.demo.R;
import com.example.binhbt.farecyclerview.multipleviewtype.model.TextItem;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.holder.FaBinderView;
import com.vn.fa.base.holder.FaViewHolder;

import butterknife.BindView;


/**
 * Created by binhbt on 7/22/2016.
 */
public class TextItemView extends FaBinderView<TextItem> {
    public TextItemView(TextItem data){
        super(data);
    }
    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        TextViewHolder holder1 = (TextViewHolder)holder;
        holder1.mTxt.setText(data.getText());
    }

    @Override
    public BinderViewHolder newHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_text;
    }

    static class TextViewHolder extends FaViewHolder {
        @BindView(R.id.text)
        TextView mTxt;
        public TextViewHolder(View view) {
            super(view);
        }
    }
}
