package com.ly.zmn48644.rpc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientMain {
    public static void main(String[] args) {
//        TimeService timeService = new TimeServiceImpl();
//
//        System.out.println(timeService.getTime("中国"));
//        System.out.println(timeService.getTime("美国"));

        ApplicationContext context = new ClassPathXmlApplicationContext("thunder-server.xml");
        System.out.println("service启动完成!");
    }
}
