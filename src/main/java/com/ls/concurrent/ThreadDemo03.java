package com.ls.concurrent;

public class ThreadDemo03 implements Runnable{
    private Res res;
    private int count;
    public ThreadDemo03(Res res) {
        this.res = res;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                if (res.isFlag()) {
                    try {
                        res.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    res.setName("小明");
                    res.setSex("男");
                }
                else {
                    res.setName("小红");
                    res.setSex("女");
                }
                count = (count + 1) % 2;
                res.setFlag(true);
                res.notify();
            }
        }
    }
}
