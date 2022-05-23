package org.fwx.hbase.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 测试 HBase connection 工具类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHBaseConnectionUtil {

    /**
     * hbase-site.xml 获取 hbase connection
     * @throws IOException
     */
    @Test
    public void testGetConnection() throws IOException {
        Connection connection = HBaseConnectionUtil.getConnection();
        System.out.println(connection);
    }

    /**
     * 配 zk 集群获取 hbase connection
     * @throws IOException
     */
    @Test
    public void testGetConnectionByZks() throws IOException {
        Connection connection = HBaseConnectionUtil.getConnectionByZks("BD1:2181,BD2:2181,BD3:2181");
        System.out.println(connection);
    }
}
