package com.example.binhbt.demo.model;

import java.util.List;

/**
 * Created by binhbt on 6/22/2016.
 */
public class MixUser {
    private List<User> lsUser;
    private User user;
    public MixUser(List<User> lsUser, User user){
        this.lsUser = lsUser;
        this.user = user;
    }
}
