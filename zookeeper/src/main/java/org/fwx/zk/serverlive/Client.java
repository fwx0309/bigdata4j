package org.fwx.zk.serverlive;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 客户端监听服务器注册信息
 */
public class Client {
    private String connections = "";
    private int sessionOutTime = 0;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        // 1.获取zookeeper
        client.loadConf();
        client.instanceCli();
        // 2.监听 zk 中服务器节点信息
        client.listen();
        
        // 阻止进程结束
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 加载配置文件
     * @throws Exception
     */
    private void loadConf() throws Exception{
        Properties props = new Properties();
        FileInputStream is = new FileInputStream("D:\\2-develop\\IdeaWorkSpace\\bigdata4j\\zookeeper\\src\\main\\resources\\conf\\conf.properties");
        props.load(is);
        connections = props.getProperty("servers");
        sessionOutTime = Integer.parseInt(props.getProperty("sessionOutTime"));
    }

    /**
     * 初始化 zk 客户端
     * @throws Exception
     */
    private void instanceCli() throws Exception {
        zooKeeper = new ZooKeeper(connections, sessionOutTime, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    listen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * servers 路径监听
     * @throws Exception
     */
    private void listen() throws Exception {
        Stat exists = zooKeeper.exists("/servers", false);

        if (exists == null) {
            System.out.println("/servers is not exists!");
            System.exit(1);
        } else {
            // 所有服务器名
            ArrayList<String> serverNames = new ArrayList<>();

            List<String> children = zooKeeper.getChildren("/servers", true);
            for (String child : children) {
                byte[] data = zooKeeper.getData("/servers/" + child, false, null);
                serverNames.add(new String(data));
            }

            System.out.println("serverNames:" + serverNames);
        }
    }
}
