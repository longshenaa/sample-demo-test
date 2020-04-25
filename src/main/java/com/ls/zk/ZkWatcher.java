package com.ls.zk;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZkWatcher implements Watcher {
    //zookeeper集群地址
    private static final String CONTENT_ADDR = "192.168.93.3:2181,192.168.93.4:2181,192.168.93.5:2181";
    private static final int SESSION_TIMEOUT = 2000;
    private ZooKeeper zooKeeper;
    public ZkWatcher() {
        createConnection(CONTENT_ADDR, SESSION_TIMEOUT);
    }
    //zk节点发生变更
    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState state = watchedEvent.getState();
        Event.EventType type = watchedEvent.getType();
        String path = watchedEvent.getPath();
        System.out.println("zookeeper keeperState:" + state + ";eventType:" + type);
        if (Event.KeeperState.SyncConnected == state) {
            if (Event.EventType.None == type) {
                System.out.println("建立zk连接成功！");
            }
            if (Event.EventType.NodeCreated == type) {
                System.out.println("节点创建成功:" + path);
            }
            if (Event.EventType.NodeDataChanged == type) {
                System.out.println("节点数据更改成功：" + path);
            }
            if (Event.EventType.NodeDeleted == type) {
                System.out.println("节点删除成功：" + path);
            }
        }
    }
    //创建连接
    public void createConnection(String address, int sessionTimeOut) {
        try {
            zooKeeper = new ZooKeeper(address, sessionTimeOut, this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //创建节点
    public void createNode(String path, String data) {
        try {
            zooKeeper.exists(path, true);
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点成功！");
        }
        catch (KeeperException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //修改节点
    public void updateNode(String path, String data) {
        try {
            zooKeeper.exists(path, true);
            zooKeeper.setData(path, data.getBytes(), -1);
        }
        catch (KeeperException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //删除节点
    public void deleteNode(String path) {
        try {
            zooKeeper.delete(path, -1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (KeeperException e) {
            e.printStackTrace();
        }
    }
    //关闭连接
    public void close(){
        try {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ZkWatcher zkWatcher = new ZkWatcher();
        zkWatcher.createNode("/zkWatcher1", "hello");
        zkWatcher.close();
    }
}
