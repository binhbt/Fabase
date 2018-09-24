package com.example.binhbt.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.binhbt.demo.R;
import com.example.binhbt.demo.adapter.UsersAdapter;
import com.example.binhbt.demo.adapter.UsersLayoutManager;
import com.example.binhbt.demo.model.MixUser;
import com.example.binhbt.demo.model.User;
import com.example.binhbt.demo.ui.activity.UserDetailActivity;
import com.vn.fa.net.RequestLoader;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by binhbt on 6/8/2016.
 */
public class UserListFragment extends BaseFragment {
    private UsersAdapter usersAdapter;
    @BindView(R.id.rv_users)
    RecyclerView rv_users;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R.id.bt_retry)
    Button bt_retry;

    public UserListFragment() {
        setRetainInstance(true);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setupRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_list;
    }

    private void startSubcriptions() {

        new RequestLoader.Builder()
                .api(api.userEntityList())
                .callback(new RequestLoader.CallBack<List<User>>() {
                    @Override
                    public void onStart() {
                        Toast.makeText(getActivity(), "onstart ", Toast.LENGTH_LONG).show();
                        hideRetry();
                        showLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getActivity(), "got error " + t.toString(), Toast.LENGTH_LONG).show();
                        // Handle error
                        hideLoading();
                        showError("Request failed");
                        showRetry();
                    }

                    @Override
                    public void onFinish(List<User> result) {
                        hideLoading();
                        renderUserList(result);
                    }

                })
                .container(UserListFragment.this)
                .tag("get_list")
                .build();
//        zipTest();
//        flatMapTest();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            startSubcriptions();
        }
    }

    private void setupRecyclerView() {
        usersAdapter = new UsersAdapter(getActivity());
        this.usersAdapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onUserItemClicked(User userModel) {
                Intent i = new Intent(getActivity(), UserDetailActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("user", userModel);
                i.putExtras(b);
                startActivity(i);
            }
        });
        this.rv_users.setLayoutManager(new UsersLayoutManager(getActivity()));
        this.rv_users.setAdapter(usersAdapter);
    }

    @Override
    public void onDestroyView() {
        rv_users.setAdapter(null);
        super.onDestroyView();
    }

    private void showError(String message) {
        this.showToastMessage(message);
    }

    private void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    private void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserListFragment.this.startSubcriptions();
    }

    private void renderUserList(Collection<User> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setUsersCollection(userModelCollection);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }
    private void zipTest(){
        Observable<MixUser> combined = Observable.zip(api.userEntityList(), api.userEntityById(1), new BiFunction<List<User>, User, MixUser>() {
            @Override
            public MixUser apply(List<User> lsUser, User user) {
                return new MixUser(lsUser, user);
            }
        });
        new RequestLoader.Builder()
                .api(combined)
                .container(this)
                .callback(new RequestLoader.CallBack<MixUser>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onFinish(MixUser result) {
                        Log.e("mix user", result.toString());
                    }
                })
                .build();
    }
    private void flatMapTest(){
        Observable<User> flat = api.userEntityList().flatMap(ls->api.userEntityById(ls.get(0).getUserId()));
        new RequestLoader.Builder()
                .api(flat)
                .tag("get_detail_first")
                .container(this)
                .callback(new RequestLoader.CallBack<User>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onFinish(User result) {
                        Log.e("First user", result.toString());
                    }
                });
    }
}