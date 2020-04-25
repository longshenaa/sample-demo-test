package com.ls.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkDemo {
    private static final String CONTENT_ADDR = "192.168.93.3:2181,192.168.93.4:2181,192.168.93.5:2181";
    private static final Integer SESSION_TIME = 2000;
    private static final CountDownLatch LATCH = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(CONTENT_ADDR, SESSION_TIME, new ZkWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //获取事件的状态
                Event.KeeperState state = watchedEvent.getState();
                Event.EventType type = watchedEvent.getType();
                if (Event.KeeperState.SyncConnected == state) {
                    if (Event.EventType.None == type) {
                        System.out.println("zk连接成功");
                        LATCH.countDown();
                    }
                }
            }
        });
        LATCH.await();
        String s = zooKeeper.create("/node01/node02", "node02".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out. println("创建节点成功：" + s);
        zooKeeper.close();
    }
}
