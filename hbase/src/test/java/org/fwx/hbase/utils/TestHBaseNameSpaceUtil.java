package org.fwx.hbase.utils;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHBaseNameSpaceUtil {

    private Connection connection;

    @Before
    public void init() throws IOException {
        connection=HBaseConnectionUtil.getConnectionByZks("BD1:2181,BD2:2181,BD3:2181");
    }

    /**
     * 获取所有的 namespace
     * @throws IOException
     */
    @Test
    public void testGetNameSpaces() throws IOException {
        LinkedList<String> nameSpaces = HBaseNameSpaceUtil.getNameSpaces(connection);
        for (String nameSpace : nameSpaces) {
            System.out.println(nameSpace);
        }
        HBaseConnectionUtil.close(connection);
    }

    /**
     * 判断 NameSpace 是否存在
     * @throws IOException
     */
    @Test
    public void testNSExist() throws IOException {
//        boolean b = HBaseNameSpaceUtil.nameSpaceExist(connection, "");
//        boolean b = HBaseNameSpaceUtil.nameSpaceExist(connection, null);
//        boolean b = HBaseNameSpaceUtil.nameSpaceExist(connection, "default");
        boolean b = HBaseNameSpaceUtil.nameSpaceExist(connection, "test");
        System.out.println(b);
    }

    /**
     * 创建 nameSpace
     * @throws IOException
     */
    @Test
    public void testCreateNS() throws IOException {
        boolean b = HBaseNameSpaceUtil.createNameSpace(connection, "ns2");
        System.out.println(b);
    }

    /**
     * 删除 NameSpace
     * @throws IOException
     */
    @Test
    public void testDeleteNameSpace() throws IOException {
        boolean b = HBaseNameSpaceUtil.deleteNameSpace(connection, "ns1");
        System.out.println(b);
    }
}
