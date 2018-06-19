package com.ly.zmn48644.rpc.test.impl;


import com.ly.zmn48644.rpc.test.UserService;
import com.ly.zmn48644.rpc.test.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<User> findUsersByName(String name) {
        User user = new User();
        user.setName("zmn");
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}
