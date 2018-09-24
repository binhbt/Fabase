package com.example.binhbt.demo.net;

import com.example.binhbt.demo.model.User;

import io.reactivex.Observable;


/**
 * Created by leobui on 11/9/2017.
 */

public class FlatRequest extends BaseRequest{
    @Override
    public Observable getApi() {
        UserListRequest userListRequest = new UserListRequest();
        UserDetailRequest detailRequest = new UserDetailRequest();
        Observable<User> flat = userListRequest.getApi().flatMap(ls->detailRequest.getApi());

        return flat;
    }
}