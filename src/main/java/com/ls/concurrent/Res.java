package com.ls.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Res {
    private String name;
    private String sex;
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    /**
     * false 未赋值；true 未打印；
     */
    private boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
