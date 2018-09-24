package com.example.binhbt.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.ui.fragment.UserDetailsFragment;


/**
 * Created by binhbt on 6/8/2016.
 */
public class UserDetailActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Fragment f = new UserDetailsFragment();
            f.setArguments(getIntent().getExtras());
            addFragment(R.id.fragmentContainer, f);
        }
    }
}
