package org.fwx.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestZkDemo {

    String connections = "BD1:2181,BD2:2181,BD3:2181";
    int sessionTimeOut = 2000;
    ZooKeeper zkCli = null;

    String path = "/javaNodeTest";

    @Before
    public void init() throws IOException {
        zkCli = new ZooKeeper(connections, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 持续监听
                /*try {
                    List<String> list = zkCli.getChildren(path, true);
                    System.out.println("*************** watch start *************");
                    for (String c : list) {
                        System.out.println(c);
                    }
                    System.out.println("*************** watch end *************");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    /**
     * 创建节点
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void createNode() throws InterruptedException, KeeperException {
        String path = "/javaNodeTest";
        byte[] data = "java".getBytes();
        ArrayList<ACL> acls = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        CreateMode createMode = CreateMode.PERSISTENT;
        String result = zkCli.create(path, data, acls, createMode);
        System.out.println(result);
    }

    /**
     * 观察子节点变化
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void watchChildNode() throws InterruptedException, KeeperException {

        List<String> list = zkCli.getChildren(path, true);
        for (String c : list) {
            System.out.println(c);
        }

        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 判断节点是否存在
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void nodeExists() throws InterruptedException, KeeperException {
        String path = "/javaNodeTest/t2";
        Stat stat = zkCli.exists(path, false);
        System.out.println(stat==null?"node not exists!":"node exists!");
    }
}
