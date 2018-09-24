package com.example.binhbt.demo.ui.mvpdemo;

import com.example.binhbt.demo.model.VersionApp;
import com.vn.fa.base.mvp.MvpView;

import java.util.List;

/**
 * Created by binhbt on 3/7/2017.
 */
public interface ListAppView extends MvpView{
    void loadDataToView(List<VersionApp> result);

    void showError();

}
