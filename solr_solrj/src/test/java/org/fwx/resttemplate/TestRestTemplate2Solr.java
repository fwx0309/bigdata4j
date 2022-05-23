package org.fwx.resttemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 测试 RestTemplate 操作 solr
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestTemplate2Solr {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testShowDatas(){
        // solr 的url
        // select 响应结果头类型：Content-Type: application/json;charset=UTF-8
        // query 响应结果头类型：Content-Type: text/plain;charset=UTF-8
        // RestTemplate 接收 text/plain;charset=UTF-8 会报错！！！
        String url = "http://localhost:8080/solr/collection1/select?q=*:*";

        Map result = restTemplate.getForObject(url, Map.class);

        // 获取response部分数据
        Map<String,Object> response = (Map<String,Object>)result.get("response");

        // 获取记录条数
        Integer numFound = (Integer) response.get("numFound");
        System.out.println("总记录条数：" + numFound);

        // 获取数据
        List<Map<String,String>> docs = (List<Map<String,String>>)response.get("docs");
        for (Map<String, String> doc : docs) {
            System.out.println(doc.toString());
        }

    }
}
