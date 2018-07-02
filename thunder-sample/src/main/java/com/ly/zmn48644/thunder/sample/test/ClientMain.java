package com.ly.zmn48644.thunder.sample.test;


import com.ly.zmn48644.thunder.sample.model.User;
import com.ly.zmn48644.thunder.sample.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ClientMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("thunder-client.xml");
        UserService userService = (UserService) context.getBean("userService");
        //BlogService blogService = (BlogService) context.getBean("blogService");


        for (int i = 0; i < 1000000; i++) {
            List<User> list = userService.findUsersByName("zmn");
            for (User user : list) {
                System.out.println(i+"--"+user.toString());
            }
        }

    }
}
