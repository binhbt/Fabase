package com.example.binhbt.demo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.model.User;
import com.squareup.picasso.Picasso;
import com.vn.fa.net.RequestLoader;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by binhbt on 6/8/2016.
 */
public class UserDetailsFragment extends BaseFragment {
    @BindView(R.id.iv_cover)
    ImageView iv_cover;
    @BindView(R.id.tv_fullname)
    TextView tv_fullname;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_followers)
    TextView tv_followers;
    @BindView(R.id.tv_description)
    TextView tv_description;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R.id.bt_retry)
    Button bt_retry;
    public UserDetailsFragment() {
        setRetainInstance(true);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
//        ButterKnife.bind(this, fragmentView);
//        return fragmentView;
//    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_details;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }

//    private void showLoading() {
//        this.rl_progress.setVisibility(View.VISIBLE);
//        this.getActivity().setProgressBarIndeterminateVisibility(true);
//    }
//
//    private void hideLoading() {
//        this.rl_progress.setVisibility(View.GONE);
//        this.getActivity().setProgressBarIndeterminateVisibility(false);
//    }

    private void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    private void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    private void showError(String message) {
        this.showToastMessage(message);
    }
    private void renderUser(User user) {
        if (user != null) {
            Picasso.with(getActivity()).load(user.getCoverUrl()).into(this.iv_cover);
            this.tv_fullname.setText(user.getFullName());
            //this.tv_email.setText(user.getEmail());
            this.tv_followers.setText(String.valueOf(user.getFollowers()));
            this.tv_description.setText(user.getDescription());
        }
    }
    private void loadUserDetails() {
        User user= (User)getArguments().getSerializable("user");
        new RequestLoader.Builder()
                .api(api.userEntityById(0))
                .callback(new RequestLoader.CallBack<User>() {
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
                    public void onFinish(User result) {
                        //Log.e("Got result ", result.toString());
                        //Toast.makeText(getActivity(), "got error "+result.toString(), Toast.LENGTH_LONG).show();
                        // Update UI on the main thread
                        hideLoading();
                        renderUser(result);
                        //Log.e("response", result.toString());
                    }
                })
                .container(this)
                .tag("get_list")
                .build();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
