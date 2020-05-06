package com.ls.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user1 = (User) context.getBean("user1");
        System.out.println(user1 != null ? user1.getName() + ";" + user1.getUserId() : "");
        User user2 = (User) context.getBean("user2");
        System.out.println(user2 != null ? user2.getName() + ";" + user2.getUserId() : "");
    }
}
