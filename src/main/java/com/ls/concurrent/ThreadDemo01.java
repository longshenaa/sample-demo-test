package com.ls.concurrent;

public class ThreadDemo01 {
    public static void main(String[] args) {
        System.out.println("hello world");
        //上面的任务执行完了，我才能执行。
        ThreadDemo02 threadDemo02 = new ThreadDemo02();
        threadDemo02.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("hellow" + i);
        }
    }
}
