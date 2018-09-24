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
public class BroardCatsTestFragment extends FaFragment {
    @BindView(R.id.result)
    TextView result;
    public static BroardCatsTestFragment newInstance() {
        return new BroardCatsTestFragment();
    }
    @OnClick(R.id.add_fragment)
    public void addFragment(){
        Fragment f = new BroardCastTestFragment2();
        ((BaseActivity)getActivity()).addFragment(R.id.fragmentContainer, f);
    }
    @OnClick(R.id.send_event)
    public void sendEventTest(){
        EventBus.getDefault().post("Broadcast 1");
    }
    public BroardCatsTestFragment() {
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
        Log.e("onevent on fragment 1", event.toString());
        result.setText(event.toString() +"TO FRAGMENT1");
    }
    @Override
    public void handleEvent(Object event){
        //TODO Handle message reived here. Overite it
        Log.e("onhandleevent fragment1", event.toString());

    }
}