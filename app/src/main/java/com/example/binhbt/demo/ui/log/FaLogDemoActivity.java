package com.example.binhbt.demo.ui.log;

import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.widget.TextView;

import com.example.binhbt.demo.BuildConfig;
import com.example.binhbt.demo.R;
import com.example.binhbt.demo.ui.activity.BaseActivity;
import com.vn.fa.base.util.FaLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leobui on 1/4/2018.
 */

public class FaLogDemoActivity extends BaseActivity{
    @BindView(R.id.txt_tag)TextView txtTag;
    @BindView(R.id.txt_log)TextView txtLog;
    @OnClick(R.id.btn_show_log)
    public void showLog(){
        FaLog.e(txtTag.getText().toString()+"", txtLog.getText().toString()+"");
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        FaLog.init(BuildConfig.DEBUG);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fa_log;
    }
}
