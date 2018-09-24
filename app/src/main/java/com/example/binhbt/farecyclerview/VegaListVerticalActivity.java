package com.example.binhbt.farecyclerview;

import android.support.v7.widget.LinearLayoutManager;

import com.example.binhbt.demo.R;

/**
 * Created by binhbt on 6/27/2016.
 */
public class VegaListVerticalActivity extends VegaBaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.vega_activity_list_vertical;
    }

    @Override
    protected boolean isSwipeToDismissEnabled() {
        return false;
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return null;
    }
}
