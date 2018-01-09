package com.ly.zmn48644.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServerMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("thunder-server.xml");
        System.out.println("service启动完成!");


//        // Instantiate HttpClient
//        HttpClient httpClient = new HttpClient();
//// Configure HttpClient, for example:
//        httpClient.setFollowRedirects(false);
//
//// Start HttpClient
//        httpClient.start();

    }
}
