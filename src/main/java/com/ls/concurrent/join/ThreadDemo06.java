package com.ls.concurrent.join;

public class ThreadDemo06 implements Runnable{
    private String name;
    public ThreadDemo06(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("thread-" + name + "-" + i);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args){
        Thread thread1 = new Thread(new ThreadDemo06("A"));
        Thread thread2 = new Thread(new ThreadDemo06("B"));
        thread1.start();
//        try {
//            thread1.join();
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread2.start();
    }
}
