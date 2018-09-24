package com.example.binhbt.farecyclerview.multipleviewtype;

import android.support.v7.widget.GridLayoutManager;

import com.example.binhbt.demo.R;

/**
 * Created by binhbt on 7/22/2016.
 */
public class GridVerticalMultipleViewActivity  extends VegaBaseMultipleViewActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.vega_activity_grid_vertical;
    }

    @Override
    protected boolean isSwipeToDismissEnabled() {
        return false;
    }

    @Override
    protected GridLayoutManager getLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position ==0 || position ==8){
                    return 2;
                }
                return 1;
            }
        });
        return layoutManager;
    }
}
