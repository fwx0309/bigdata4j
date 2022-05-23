package org.fwx.configuration;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class SolrConfig {

    @Value("${solr-url}")
    private String url;

    /**
     * solrj 访问单节点 solr 的类
     * @return HttpSolrClient
     */
    @Bean
    public HttpSolrClient httpSolrClient(){
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(url);
        return builder.build();
    }

    // solrcloud zookeeper集群hosts
    @Value("${zks}")
    private String zks;

    /**
     * solrj 访问 solrcloud 的类
     * @return CloudSolrClient
     */
    @Bean
    public CloudSolrClient cloudSolrClient(){
        // zookeeper hosts
        String[] zkhosts = zks.split(",");
        ArrayList<String> zklists = new ArrayList<>();
        for (String zkhost : zkhosts) {
            zklists.add(zkhost);
        }

        CloudSolrClient.Builder builder = new CloudSolrClient.Builder(zklists, Optional.empty());
        CloudSolrClient cloudSolrClient = builder.build();
        // 设置超时时间
        cloudSolrClient.setZkClientTimeout(10000);
        // 设置默认collection
        cloudSolrClient.setDefaultCollection("myCollection1");

        return cloudSolrClient;
    }
}
