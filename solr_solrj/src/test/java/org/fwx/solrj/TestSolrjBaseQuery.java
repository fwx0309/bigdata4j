package org.fwx.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * solrj 基础查询
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolrjBaseQuery {
    @Autowired
    private HttpSolrClient httpSolrClient;

    /**
     * 使用 query 查询
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testQuery() throws SolrServerException, IOException {
        // 封装查询条件的类
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:华为");

        // 执行查询返回结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析结果输出
        SolrDocumentList results = response.getResults();
        // 结果数据量
        System.out.println("结果数据量:" + results.getNumFound());
        // 数据列表
        for (SolrDocument document : results) {
            System.out.println(document);
        }
    }

    /**
     * 使用 query + filter 查询
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testQueryAndFilter() throws SolrServerException, IOException {
        // 封装查询条件的类
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手机");
        query.addFilterQuery("item_brand:华为");
        query.addFilterQuery("item_price:[1000 TO 2000]");

        // 执行查询返回结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析结果输出
        SolrDocumentList results = response.getResults();
        // 结果数据量
        System.out.println("结果数据量:" + results.getNumFound());
        // 数据列表
        for (SolrDocument document : results) {
            System.out.println(document);
        }
    }

    /**
     * 分页查询
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testPaging() throws SolrServerException, IOException {
        // 当前页
        int currentPage = 1;
        // 每页显示条数
        int pageSize = 5;

        // 封装查询条件的类
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手机");
        // 设置当前查询页
        query.setStart((currentPage - 1) * pageSize);
        // 设置每页显示条数
        query.setRows(pageSize);

        // 执行查询返回结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析结果输出
        SolrDocumentList results = response.getResults();
        // 结果数据量
        System.out.println("结果数据量:" + results.getNumFound());
        // 数据列表
        for (SolrDocument document : results) {
            System.out.println(document);
        }
    }

    /**
     * 排序查询
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testSort() throws SolrServerException, IOException {
        // 封装查询条件的类
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手机");
        query.setSort("item_price", SolrQuery.ORDER.desc);
        query.setSort("id",SolrQuery.ORDER.asc);

        // 执行查询返回结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析结果输出
        SolrDocumentList results = response.getResults();
        // 结果数据量
        System.out.println("结果数据量:" + results.getNumFound());
        // 数据列表
        for (SolrDocument document : results) {
            System.out.println(document);
        }
    }

    /**
     * 组合查询
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testCombination() throws SolrServerException, IOException {
        // 封装查询条件的类
        SolrQuery query = new SolrQuery();
        //query.setQuery("item_title:手机 OR item_title:'平板电视'");
        query.setQuery("item_title:手机 AND item_brand:华为");
        query.setStart(0);
        query.setRows(1000);

        // 执行查询返回结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析结果输出
        SolrDocumentList results = response.getResults();
        // 结果数据量
        System.out.println("结果数据量:" + results.getNumFound());
        // 数据列表
        for (SolrDocument document : results) {
            System.out.println(document);
        }
    }
}
