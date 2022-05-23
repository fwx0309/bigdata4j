package org.fwx.hbase.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * HBase connection 工具类
 * connection 的创建和关闭
 * connection 是线程安全的
 */
public class HBaseConnectionUtil {
    /**
     * 获取 hbase.client.Connection
     * resource 中需要有 hdfs-site.xml/coro-site.xml/hbase-site.xml
     *
     * @return org.apache.hadoop.hbase.client.Connection
     */
    public static Connection getConnection() throws IOException {
        Connection connection = ConnectionFactory.createConnection();
        return connection;
    }

    /**
     * 获取 hbase.client.Connection
     *
     * @param zks 配 zookeeker 集群获取 hbase connection
     * @return org.apache.hadoop.hbase.client.Connection
     * @throws IOException
     */
    public static Connection getConnectionByZks(String zks) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", zks);
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    /**
     * 关闭连接
     *
     * @param connection
     */
    public static void close(Connection connection) throws IOException {
        connection.close();
    }
}
