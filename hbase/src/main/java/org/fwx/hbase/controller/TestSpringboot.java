package org.fwx.hbase.controller;

import org.apache.hadoop.hbase.client.Connection;
import org.fwx.hbase.utils.HBaseConnectionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestSpringboot {

    @RequestMapping("/")
    public String testWeb() throws IOException {
        Connection connection = HBaseConnectionUtil.getConnection();
        System.out.println(connection);
        return "welcome !";
    }
}
