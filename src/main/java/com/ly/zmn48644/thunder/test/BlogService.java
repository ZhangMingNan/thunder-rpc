package com.ly.zmn48644.thunder.test;


import com.ly.zmn48644.thunder.test.model.Blog;

/**
 * 作者：张明楠（wechat:zhangmingnan1990）
 * 时间：2018/6/16
 */

public interface BlogService {
    Blog findBlogById(Integer id);
    int totalCount();
}
