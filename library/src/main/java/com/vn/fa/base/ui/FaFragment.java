package com.vn.fa.base.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vn.fa.base.R;
import com.vn.fa.base.callback.OnNetWorkStatusChanged;
import com.vn.fa.base.event.NetWorkEvent;
import com.vn.fa.base.util.FontHelper;
import com.vn.fa.base.util.NetworkUtil;
import com.vn.fa.ui.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by binhbt on 6/22/2016.
 */
public abstract class FaFragment extends RxFragment{
    protected OnNetWorkStatusChanged onNetWorkStatusChanged;
    protected View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_vega, container,
                    false);
            if (getLayoutId() >0) {
                View contentFragment = inflater.inflate(getLayoutId(), container,
                        false);
                ((ViewGroup) root.findViewById(R.id.fragment_vega_content))
                        .addView(contentFragment);
            }
            if (isShowNoNetWork()) {
                addNoNetWork();
            }
            ButterKnife.bind(this, root);

            initView(savedInstanceState);
        } else {
            if (root.getParent() != null) {
                ((ViewGroup) root.getParent()).removeView(root);
            }
        }
        if (isListenOnSleep())
            EventBus.getDefault().register(this);

        if (getActivity() != null && getActivity() instanceof FaActivity &&
                ((FaActivity)getActivity()).getLoadingResource() >0){
            ProgressBar loading = (ProgressBar) root.findViewById(R.id.loading);
            loading.setIndeterminateDrawable(getResources().getDrawable(((FaActivity)getActivity()).getLoadingResource()));
        }
        if (isShowNoNetWork() && !NetworkUtil.isConnected(getActivity())) {
            showNoNetWork(true);
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected abstract int getLayoutId();
    protected abstract void initView(Bundle savedInstanceState);
    protected void showLoading(){
        if (getActivity() != null && getActivity() instanceof FaActivity &&
                ((FaActivity)getActivity()).getLoadingResource() >0){
            showLoading(((FaActivity)getActivity()).getLoadingResource());
        }else {
            root.findViewById(R.id.fragmentLoadingContanner).setVisibility(View.VISIBLE);
        }
    }
    protected void showLoading(int drawableRes){
        root.findViewById(R.id.fragmentLoadingContanner).setVisibility(View.VISIBLE);
        if (drawableRes >0) {
            ProgressBar loading = (ProgressBar) root.findViewById(R.id.loading);
            loading.setIndeterminateDrawable(getResources().getDrawable(drawableRes));
        }
    }
    protected void hideLoading(){
        root.findViewById(R.id.fragmentLoadingContanner).setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
//        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isListenOnSleep())
            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        if (!isListenOnSleep())
            EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onDestroyView() {
        if (isListenOnSleep())
            EventBus.getDefault().unregister(this);
        //Picasso.with(getActivity()).cancelTag(this);
        super.onDestroyView();

    }
    public void sendEvent(Object message){
        if (message == null) throw new IllegalArgumentException("Object message can not be null");
        EventBus.getDefault().post(message);
    }
    // This method will be called when a SomeOtherEvent is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(Object event) {
        handleEvent(event);
    }
    public void handleEvent(Object event){
        //TODO Handle message reived here. Overite it
        if (event instanceof NetWorkEvent){
            NetWorkEvent netWorkEvent = (NetWorkEvent)event;
            if (netWorkEvent.getType() == NetWorkEvent.Type.NETWORK_STATUS_CHANGED){
                if (onNetWorkStatusChanged != null){
                    onNetWorkStatusChanged.onStatusChanged(netWorkEvent.getStatus());
                }
            }
        }
    }

    public OnNetWorkStatusChanged getOnNetWorkStatusChanged() {
        return onNetWorkStatusChanged;
    }

    public void setOnNetWorkStatusChanged(OnNetWorkStatusChanged onNetWorkStatusChanged) {
        this.onNetWorkStatusChanged = onNetWorkStatusChanged;
    }

    protected void loadFont(String assetName) {
        FontHelper fontChanger = new FontHelper(getActivity().getAssets(), assetName);
        fontChanger.replaceFonts((ViewGroup) (ViewGroup) this.getView());
    }
    protected boolean isListenOnSleep(){
        return false;
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    public void showFragmentChild(Fragment fragment, Bundle bundle, int layoutId) {
        fragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(layoutId, fragment)
                .commitAllowingStateLoss();
    }
    public void addFragmentChild(Fragment fragment, Bundle bundle, int layoutId) {
        fragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().add(layoutId, fragment)
                .commitAllowingStateLoss();
    }
    public void showFragmentChild(Fragment fragment, Bundle bundle, int layoutId, String tag) {
        fragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(layoutId, fragment, tag)
                .commitAllowingStateLoss();
    }
    public void removeFragmentChild(int layoutId) {
        Fragment child = getChildFragmentManager().findFragmentById(layoutId);
        if (child != null)
            getChildFragmentManager().beginTransaction().
                    remove(child).commit();
    }
    protected boolean isShowNoNetWork(){
        return true;
    }
    protected int getNoNetWorkLayout(){
        return R.layout.view_no_network;
    }
    protected void showNoNetWork(boolean isShow){
        if (isShow){
            root.findViewById(R.id.no_network_content).setVisibility(View.VISIBLE);
        }else{
            root.findViewById(R.id.no_network_content).setVisibility(View.INVISIBLE);
        }
    }
    protected void addNoNetWork(){
        if (getNoNetWorkLayout() >0) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View contentFragment = inflater.inflate(getNoNetWorkLayout(), (ViewGroup) root.findViewById(R.id.no_network_content),
                    false);
            ((ViewGroup) root.findViewById(R.id.no_network_content))
                    .addView(contentFragment);
        }
    }
}
