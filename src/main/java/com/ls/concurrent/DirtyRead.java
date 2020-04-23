package com.ls.concurrent;

import java.util.concurrent.TimeUnit;

public class DirtyRead {
    private String username = "abc";
    private String password = "123";
    public synchronized void setValue (String username, String password) throws InterruptedException {
        this.username = username;
        Thread.sleep(5000);
        this.password = password;
        System.out.println("对参数进行赋值:username = " + username + ";password = " + password);
    }
    public void getValue() {
        System.out.println("获取参数值：username = " + username + ";password = " + password);
    }
    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dirtyRead = new DirtyRead();
        new Thread(() -> {
            try {
                dirtyRead.setValue("name", "word");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        dirtyRead.getValue();
    }
}
