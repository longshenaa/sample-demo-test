package com.ls.concurrent;

public class ThreadDemo02 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("hello" + i);
        }

    }
}
