package com.ly.zmn48644.thunder.test;


import com.ly.zmn48644.thunder.test.model.User;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public interface UserService {
    List<User> findUsersByName(String name);

}