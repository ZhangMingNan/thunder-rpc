package org.ming.thunder.sample.model;

import java.io.Serializable;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class User implements Serializable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
