package com.ly.zmn48644.thunder.sample.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ServerMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("thunder-server.xml");
        System.out.println("service启动完成!");
    }
}
