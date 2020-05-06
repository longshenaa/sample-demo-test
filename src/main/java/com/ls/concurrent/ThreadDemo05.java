package com.ls.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo05 implements Runnable{
    private static AtomicInteger index = new AtomicInteger();
    private static  ExecutorService executorService = new ThreadPoolExecutor(0, 100, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            int i = index.incrementAndGet();
            Thread thread = new Thread(r, "thread-pool-exe-" + i);
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());
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
