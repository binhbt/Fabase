package com.example.binhbt.farecyclerview.multipleviewtype.model;

import com.example.binhbt.farecyclerview.multipleviewtype.viewmodel.TextItemView;
import com.vn.fa.adapter.multipleviewtype.DataBinder;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;

/**
 * Created by binhbt on 7/22/2016.
 */
public class TextItem implements IViewBinder{
    private String text = "Hallo. How are u?";//"Một bộ phim kinh dị rất biết cách “hù” khán giả.Những khoảnh khắc kịch tính mới lạ, nghẹt thở, hấp dẫn bất ngờ đúng kiể";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public DataBinder getViewBinder() {
        return new TextItemView(this);
    }
}
