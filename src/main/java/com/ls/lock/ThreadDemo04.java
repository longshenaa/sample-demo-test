package com.ls.lock;

import com.ls.concurrent.Res;

public class ThreadDemo04 implements Runnable{
    private Res res;

    public ThreadDemo04(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            res.lock.lock();
            try {
                if (!res.isFlag()) {
                    try {
                        res.condition.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("姓名：" + res.getName() + ";性别：" + res.getSex());
                res.setFlag(false);
                res.condition.signal();
            }
            finally {
                res.lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Res res = new Res();
        ThreadDemo03 threadDemo03 = new ThreadDemo03(res);
        ThreadDemo04 threadDemo04 = new ThreadDemo04(res);
        new Thread(threadDemo03).start();
        new Thread(threadDemo04).start();
    }
}
