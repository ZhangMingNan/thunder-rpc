package org.ming.thunder.sample.impl;


import org.ming.thunder.sample.service.BlogService;
import org.ming.thunder.sample.model.Blog;

import java.util.concurrent.TimeUnit;

/**
 * 作者：张明楠
 * 时间：2018/6/16
 */
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog findBlogById(Integer id) {
        return new Blog(1, "motan框架入门", "简单高效的rpc框架");
    }

    @Override
    public int totalCount() {
        System.out.println("开始处理。。");
        try {
            //模拟服务端处理延迟一秒钟
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("处理结束。。");
        return 100;
    }
}
