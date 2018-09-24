package com.example.binhbt.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.ui.fragment.BroardCatsTestFragment;

/**
 * Created by binhbt on 8/12/2016.
 */
public class BroardCastTestActivity extends BaseActivity{
    @Override
    protected void initView(Bundle savedInstanceState) {
        Fragment f = new BroardCatsTestFragment();
        f.setArguments(getIntent().getExtras());
        addFragment(R.id.fragmentContainer, f);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout;
    }
}
