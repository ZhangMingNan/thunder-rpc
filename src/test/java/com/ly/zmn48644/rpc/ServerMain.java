package com.ly.zmn48644.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("thunder-server.xml");
        System.out.println("service启动完成!");
    }
}
