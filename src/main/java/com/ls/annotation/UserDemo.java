package com.ls.annotation;
@SetTable(value = "user_demo")
public class UserDemo {
    @SetProperty(value = "user_name")
    private String name;
    @SetProperty(value = "user_age")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
