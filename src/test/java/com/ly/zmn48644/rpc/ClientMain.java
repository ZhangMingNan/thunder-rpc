package com.ly.zmn48644.rpc;

import com.ly.zmn48644.rpc.test.TimeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("thunder-client.xml");
        TimeService timeService   = (TimeService)context.getBean("timeService");
        for (int i= 0;i<10000;i++){
            String time = timeService.getTime("中国");
            System.out.println("----"+time);
        }

    }
}
