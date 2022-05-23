package org.fwx.hbase.utils;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.RegionSplitter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHBaseTableUtil {

    private Connection connection;

    @Before
    public void init() throws IOException {
        connection = HBaseConnectionUtil.getConnectionByZks("BD1:2181,BD2:2181,BD3:2181");
    }

    /**
     * 判断 NameSpace 是否存在
     *
     * @throws IOException
     */
    @Test
    public void testTabelNameExist() throws IOException {
        boolean b = HBaseTableUtil.tableExists(connection, null, "t2");
        System.out.println(b);
    }

    /**
     * 创建 table
     *
     * @throws IOException
     */
    @Test
    public void testCreateTable() throws IOException {
        boolean b = HBaseTableUtil.createTable(connection, null, "t2", "cf1", "cf2");
        System.out.println(b);
    }

    /**
     * 用 hbase server 中自带的两个 rowKey 算法预分区创建表
     *
     * @throws IOException
     */
    @Test
    public void testCreateTableByAlgo() throws IOException {
        boolean b = HBaseTableUtil.createTableBySplitAlgo(connection, 12, new RegionSplitter.HexStringSplit(), "ns1", "splitsHex", "cf1", "cf2");
        System.out.println(b);
    }

    /**
     * 删除 table
     *
     * @throws IOException
     */
    @Test
    public void testDeleteTable() throws IOException {
        boolean b = HBaseTableUtil.deleteTable(connection, null, "t2");
        System.out.println(b);
    }
}