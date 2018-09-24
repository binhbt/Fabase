package com.example.binhbt.demo.net;

import com.example.binhbt.demo.model.MixUser;
import com.example.binhbt.demo.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by leobui on 11/9/2017.
 */

public class MixRequest extends BaseRequest{
    @Override
    public Observable getApi() {
        UserListRequest userListRequest = new UserListRequest();
        UserDetailRequest detailRequest = new UserDetailRequest();
        Observable<MixUser> combined = Observable.zip(userListRequest.getApi(), detailRequest.getApi(), new BiFunction<List<User>, User, MixUser>() {
            @Override
            public MixUser apply(List<User> lsUser, User user) {
                return new MixUser(lsUser, user);
            }
        });
        return combined;
    }
}
