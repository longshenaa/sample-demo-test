package com.ls.spring;

public class User {
    private String userId;
    private String name;

    public User() {
        System.out.println("调用了空参构造方法！");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
