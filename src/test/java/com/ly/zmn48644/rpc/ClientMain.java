package com.ly.zmn48644.rpc;

import com.ly.zmn48644.rpc.test.BlogService;
import com.ly.zmn48644.rpc.test.UserService;
import com.ly.zmn48644.rpc.test.model.User;
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
        BlogService blogService = (BlogService) context.getBean("blogService");

      //  int count = blogService.totalCount();
        List<User> list = userService.findUsersByName("zmn");

     //   System.out.println(count);
        System.out.println(list.size());

    }
}
