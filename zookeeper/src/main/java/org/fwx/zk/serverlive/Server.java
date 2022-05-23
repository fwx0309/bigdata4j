package org.fwx.zk.serverlive;

import org.apache.zookeeper.*;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 服务器注册
 */
public class Server {
    private String connections = "";
    private int sessionOutTime = 0;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        // 1.获取zookeeper
        server.loadConf();
        server.instanceCli();
        // 2.注册节点
        server.regist(args[0]);

        System.out.println(args[0] + "is online!");
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

            }
        });
    }

    /**
     * 注册服务器到 zk 中
     * @param serverName
     * @throws InterruptedException
     * @throws KeeperException
     */
    private void regist(String serverName) throws InterruptedException, KeeperException {
        String path = zooKeeper.create("/servers/server", serverName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("regist path " + path);
    }
}
