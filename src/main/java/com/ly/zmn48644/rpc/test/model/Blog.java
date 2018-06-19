package com.ly.zmn48644.rpc.test.model;



import java.io.Serializable;

/**
 * 作者：张明楠（wechat:zhangmingnan1990）
 * 时间：2018/6/16
 */


public class Blog implements Serializable {
    private Integer id;
    private String title;
    private String content;

    public Blog() {
    }

    public Blog(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
