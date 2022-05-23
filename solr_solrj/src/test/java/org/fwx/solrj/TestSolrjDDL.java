package org.fwx.solrj;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * solrCore 管理
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolrjDDL {

    @Autowired
    private HttpSolrClient httpSolrClient;

    /**
     * 创建 solrCore
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testCreateSolrCore() throws SolrServerException, IOException {
        CoreAdminRequest.createCore("collection2", "D:\\2-develop\\SolrHome\\collection2",httpSolrClient);
    }

    /**
     * 重载 solrCore
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testReloadSolrCore() throws SolrServerException, IOException {
        CoreAdminRequest.reloadCore("collection2",httpSolrClient);
    }

    /**
     * solrCore 重命名
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testRenameSorlCore() throws SolrServerException, IOException {
        //CoreAdminRequest.renameCore("collection2", "collection2new", httpSolrClient);
        CoreAdminRequest.renameCore("collection2new", "collection2", httpSolrClient);
    }

    /**
     * 卸载 solrCore
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testUnloadSolrCore() throws SolrServerException, IOException {
        CoreAdminRequest.unloadCore("collection2", httpSolrClient);
    }

    /**
     * 交换两个 solrCore 中的数据
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testSwapSolrCore() throws SolrServerException, IOException {
        CoreAdminRequest.swapCore("collection1", "collection2", httpSolrClient);
    }
}
