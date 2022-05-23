package org.fwx.hbase.utils;

import org.apache.hadoop.hbase.client.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHBaseDataUtil {
    private Connection connection;

    @Before
    public void init() throws IOException {
        connection = HBaseConnectionUtil.getConnectionByZks("BD1:2181,BD2:2181,BD3:2181");
    }

    /**
     * put data
     *
     * @throws IOException
     */
    @Test
    public void testPut() throws IOException {
        HBaseDataUtil.putData(connection, "ns1", "t1", "codeR1", "cf1", "name", "codeName1");
    }

    /**
     * 根据 rowKey 查询数据
     */
    @Test
    public void testGetData() throws IOException {
        String one = HBaseDataUtil.getOne(connection, "fwx", "t1", "k1");
        System.out.println(one);
    }

    /**
     * scan 查询数据
     */
    @Test
    public void testScanData() throws IOException {
        List<String> list = HBaseDataUtil.scan(connection, "fwx", "t1");
        for (String data : list) {
            System.out.println(data);
        }
    }

    /**
     * 根据 rowKey 删除一条数据
     *
     * @throws IOException
     */
    @Test
    public void testDelete() throws IOException {
        HBaseDataUtil.deleteOne(connection, "ns1", "t1", "r1");
    }
}
