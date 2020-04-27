package com.ls.zk.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistributeLock extends ZookeeperAbstractLock {
    private CountDownLatch latch = null;
    @Override
    protected boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }
        catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    protected void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                if (latch != null) {
                    latch.countDown();
                }
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
            }
        };
        //注册监听
        zkClient.subscribeDataChanges(PATH, iZkDataListener);
        if (zkClient.exists(PATH)) {
            latch = new CountDownLatch(1);
            try {
                latch.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
