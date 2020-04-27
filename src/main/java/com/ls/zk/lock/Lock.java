package com.ls.zk.lock;

public interface Lock {
    //获取锁
    void getLock();
    //解锁
    void unLock();

}
