package com.ls.concurrent;

public class ThreadDemo05 implements Runnable{
    public volatile boolean flag = false;

    @Override
    public void run() {
        System.out.println("线程开始执行！");
        while (!flag) {

        }
        System.out.println("线程结束执行！");
    }
    public static void main(String[] args) {
        ThreadDemo05 threadDemo05 = new ThreadDemo05();
        new Thread(threadDemo05).start();
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadDemo05.flag = true;
        System.out.println("线程状态：" + threadDemo05.flag);
    }
}
