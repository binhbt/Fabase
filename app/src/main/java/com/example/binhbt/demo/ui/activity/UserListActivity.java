package com.example.binhbt.demo.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.ui.fragment.UserListFragment;


/**
 * Created by binhbt on 6/8/2016.
 */
public class UserListActivity extends BaseActivity{
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
            addFragment(R.id.fragmentContainer, new UserListFragment());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout;
    }

    @Override
    protected boolean isToolBar() {
        return true;
    }
    @Override
    protected int getMenuLayout() {
        return R.menu.menu_ex7;
    }

    @Override
    protected boolean isBackEnabled() {
        return true;
    }
}
