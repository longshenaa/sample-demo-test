package com.ls.zk.lock;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements Lock{
    //zookeeper集群地址
    private static final String CONTENT_ADDR = "192.168.93.3:2181,192.168.93.4:2181,192.168.93.5:2181";
    protected static final String PATH = "/lock";
    protected ZkClient zkClient = new ZkClient(CONTENT_ADDR);
    @Override
    public void getLock() {
        if (tryLock()) {
            System.out.println("获取到锁的资源");
        }
        else {
            waitLock();
            getLock();
        }
    }
    protected abstract boolean tryLock();
    protected abstract void waitLock();
    @Override
    public void unLock() {
        if (zkClient != null) {
            zkClient.close();
        }
    }
}
