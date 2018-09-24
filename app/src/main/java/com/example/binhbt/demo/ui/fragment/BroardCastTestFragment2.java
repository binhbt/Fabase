package com.example.binhbt.demo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.ui.activity.BaseActivity;
import com.vn.fa.base.ui.FaFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by binhbt on 8/12/2016.
 */
public class BroardCastTestFragment2 extends FaFragment {
    @BindView(R.id.result)
    TextView result;
    @OnClick(R.id.add_fragment)
    public void addFragment(){
        Fragment f = new BroardCatsTestFragment();
        ((BaseActivity)getActivity()).addFragment(R.id.fragmentContainer, f);
    }
    @OnClick(R.id.send_event)
    public void sendEventTest(){
        EventBus.getDefault().post("Broadcast 2");
    }
    public static BroardCastTestFragment2 newInstance() {
        return new BroardCastTestFragment2();
    }

    public BroardCastTestFragment2() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_broardcast_test;
    }
    @Subscribe
    public void onEvent(Object event) {
        Log.e("onevent on fragment 2", event.toString());
        result.setText(event.toString()+"to fragment2");
    }
    @Override
    public void handleEvent(Object event){
        //TODO Handle message reived here. Overite it
        Log.e("onhandleevent fragment2", event.toString());

    }
}