package com.vn.fa.base.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vn.fa.base.R;
import com.vn.fa.base.callback.OnNetWorkStatusChanged;
import com.vn.fa.base.event.NetWorkEvent;
import com.vn.fa.base.util.FontHelper;
import com.vn.fa.ui.RxActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by binhbt on 6/22/2016.
 */
public abstract class FaActivity extends RxActivity{
    protected OnNetWorkStatusChanged onNetWorkStatusChanged;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vega);
        if (getLayoutId() >0) {
            View contentView = getLayoutInflater().inflate(getLayoutId(), null);
            ((ViewGroup) findViewById(R.id.vega_content)).addView(contentView);
        }
        if (isToolBar()){
            addToolBar();
        }
        ButterKnife.bind(this);
        initView(savedInstanceState);
        if (isListenOnSleep())
            EventBus.getDefault().register(this);
    }
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract int getLayoutId();

    public void showLoading(){
        if (getLoadingResource() >0){
            showLoading(getLoadingResource());
        }else {
            findViewById(R.id.loadingContanner).setVisibility(View.VISIBLE);
        }
    }
    public void hideLoading(){
        findViewById(R.id.loadingContanner).setVisibility(View.GONE);
    }
    public void showLoading(int drawableRes){
        findViewById(R.id.loadingContanner).setVisibility(View.VISIBLE);
        ProgressBar loading = (ProgressBar)findViewById(R.id.progressbar_loading);
        loading.setIndeterminateDrawable(getResources().getDrawable(drawableRes));
    }

    public void showProgressDialog(String message, boolean cancelAble,
                                   DialogInterface.OnCancelListener onCancelListener) {
        if (progressDialog == null) {
            synchronized (this.getClass()) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(this);
                    if (getProgressDialogDrawable() != null) {
                        progressDialog.setIndeterminateDrawable(getProgressDialogDrawable());
                    }
                }
            }
        }
        progressDialog.setMessage(message);
        progressDialog.setOnCancelListener(onCancelListener);
        progressDialog.setCancelable(cancelAble);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public OnNetWorkStatusChanged getOnNetWorkStatusChanged() {
        return onNetWorkStatusChanged;
    }

    public void setOnNetWorkStatusChanged(OnNetWorkStatusChanged onNetWorkStatusChanged) {
        this.onNetWorkStatusChanged = onNetWorkStatusChanged;
    }

    public Drawable getProgressDialogDrawable() {
        if (getLoadingResource() >0) {
            return getResources().getDrawable(getLoadingResource());
        }
        return null;
    }
    public int getLoadingResource(){
        return 0;
    }
    public void showProgressDialog(String message,
                                   DialogInterface.OnCancelListener onCancelListener) {
        showProgressDialog(message, true, onCancelListener);
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
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
    protected boolean isListenOnSleep(){
        return false;
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
        //TODO Handle message received here. Overite it
        if (event instanceof NetWorkEvent){
            NetWorkEvent netWorkEvent = (NetWorkEvent)event;
            if (netWorkEvent.getType() == NetWorkEvent.Type.NETWORK_STATUS_CHANGED){
                if (onNetWorkStatusChanged != null){
                    onNetWorkStatusChanged.onStatusChanged(netWorkEvent.getStatus());
                }
            }
        }
    }
    protected void loadFont(String assetName) {
        FontHelper fontChanger = new FontHelper(getAssets(), assetName);
        fontChanger.replaceFonts((ViewGroup)findViewById(android.R.id.content));
    }

    @Override
    protected void onDestroy() {
        if (isListenOnSleep())
            EventBus.getDefault().unregister(this);
        super.onDestroy();
        Picasso.with(this).cancelTag(this);

    }
    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showFragment(Fragment fragment, Bundle bundle, int layoutId) {
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(layoutId, fragment)
                .commitAllowingStateLoss();
    }
    public void addFragment(Fragment fragment, Bundle bundle, int layoutId) {
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(layoutId, fragment)
                .commitAllowingStateLoss();
    }
    public void removeFragment(int layoutId) {
        Fragment child = getSupportFragmentManager().findFragmentById(layoutId);
        if (child != null)
            getSupportFragmentManager().beginTransaction().
                    remove(child).commit();
    }
    protected boolean isToolBar(){
        return false;
    }
    protected int getToolBarLayout(){
        return R.layout.view_toolbar;
    }
    protected void addToolBar(){
        if (getToolBarLayout() >0) {
            LayoutInflater inflater = getLayoutInflater();
            View contentFragment = inflater.inflate(getToolBarLayout(), (ViewGroup) findViewById(R.id.view_toolbar_content),
                    false);
            ((ViewGroup) findViewById(R.id.view_toolbar_content))
                    .addView(contentFragment);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (isBackEnabled()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        if (getBackButtonIcon() >0){
            getSupportActionBar().setHomeAsUpIndicator(getBackButtonIcon());
        }
    }
    protected boolean isBackEnabled(){
        return false;
    }
    protected int getMenuLayout(){
        return 0;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuLayout() >0)
        getMenuInflater().inflate(getMenuLayout(), menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(isBackEnabled() && id == android.R.id.home){
            finish();
            return true;
        }
        return onToolBarItemSelected(item);
    }
    protected boolean onToolBarItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }
    public void showBackButton(boolean isShow){
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
        getSupportActionBar().setHomeButtonEnabled(isShow);
    }
    public int getBackButtonIcon(){
        return 0;
    }
}
