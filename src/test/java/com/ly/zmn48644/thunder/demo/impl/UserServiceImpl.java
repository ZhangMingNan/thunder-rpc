package com.ly.zmn48644.thunder.demo.impl;


import com.ly.zmn48644.thunder.demo.UserService;



import java.util.ArrayList;
import java.util.List;

import com.ly.zmn48644.thunder.demo.model.User;


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
