package com.example.binhbt.demo.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.binhbt.demo.net.EndPoints;
import com.example.binhbt.demo.ui.DemoApplication;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.mvp.MvpView;
import com.vn.fa.base.ui.FaActivity;

/**
 * Created by binhbt on 6/8/2016.
 */
public class  BaseActivity extends FaActivity implements MvpView{
    protected BasePresenter presenter;
    protected EndPoints api = DemoApplication.getApi();
    public void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //Do nothing
        if (presenter != null)
        presenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        //Have no layout
        return 0;
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
        presenter.detachView();
        super.onDestroy();
    }
}
