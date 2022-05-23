package org.fwx.solrj;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.fwx.utils.MD5Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolrjDML {
    private MD5Utils md5Utils;

    @Autowired
    private HttpSolrClient httpSolrClient;

    @Before
    public void before(){
        md5Utils = MD5Utils.getInstance();
    }

    /**
     * 添加一条数据，如果数据存在则为更新操作
     */
    @Test
    public void testAddOne() throws SolrServerException, IOException {
        SolrInputDocument document = new SolrInputDocument();
        double random = Math.random();
        /*
           {"book_price":71.5,
            "book_num":4,
            "book_name":"java 编程思想",
            "id":"1",
            "book_pic":"23488292934.jpg",
            "_version_":1700919467575345152}
         */
        String id = md5Utils.getStringMd5(String.valueOf(random));
        document.addField("id",id);
        document.addField("book_price","100");
        document.addField("book_num","5");
        document.addField("book_name","小人书");
        document.addField("book_pic","小人书.jpg");
        System.out.println(id);
        httpSolrClient.add(document);
        httpSolrClient.commit();
    }

    /**
     * 根据 id 删除数据
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testDeleteById() throws SolrServerException, IOException {
        System.out.println(httpSolrClient.deleteById("366ffbe437a42a1b8a2b300ad20d0218"));
        httpSolrClient.commit();
    }

    /**
     * 根据查询条件删除数据
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testDeleteByQuery() throws SolrServerException, IOException {
        System.out.println(httpSolrClient.deleteByQuery("book_name:小人书"));
        httpSolrClient.commit();
    }
}
