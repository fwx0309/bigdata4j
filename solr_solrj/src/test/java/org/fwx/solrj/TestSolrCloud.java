package org.fwx.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.fwx.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * solrcloud 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestSolrCloud {

    @Autowired
    @Qualifier("cloudSolrClient")
    private CloudSolrClient cloudSolrClient;

    /**
     * 添加一条数据，如果数据存在则为更新操作
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testAddOne() throws SolrServerException, IOException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "solrcloud01");
        document.addField("name", "solrcloud01name");

        cloudSolrClient.add(document);
        cloudSolrClient.commit();
    }

    /**
     * 使用 query 查询
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testQuery() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");

        QueryResponse response = cloudSolrClient.query(query);

        SolrDocumentList results = response.getResults();
        System.out.println("匹配数据条数：" + results.getNumFound());
        for (SolrDocument document : results) {
            System.out.println(document);
        }
    }
}
