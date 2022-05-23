package org.fwx.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Facet 统计数据查询测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolrFacetQuery {

    @Autowired
    private HttpSolrClient httpSolrClient;

    /**
     * facet FacetMinCount 大于5
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testFacet() throws SolrServerException, IOException {
        // 设置查询条件
        // q=*:*&facet=on&facet.field=item_brand&facet.mincount=5
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFacet(true);
        query.addFacetField("item_brand");
        query.setFacetMinCount(5);

        // 获取查询结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析facet结果
        FacetField item_brand = response.getFacetField("item_brand");
        List<FacetField.Count> itemBrandValues = item_brand.getValues();
        for (FacetField.Count itemBrandValue : itemBrandValues) {
            System.out.println(itemBrandValue.getName() + ":" + itemBrandValue.getCount());
        }
    }

    /**
     * facet query
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testFacetQuery() throws SolrServerException, IOException {
        // 设置查询条件,url中查询是符号需要编码
        // q=*:*&facet=on
        // &facet.query=item_brand:华为
        // &facet.query={!key=平板电视}item_category:平板电视
        // &facet.query=item_brand:三星
        // &facet.query=item_price:[1000 TO 2000]
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFacet(true);
        query.addFacetQuery("item_brand:华为");
        query.addFacetQuery("{!key=平板电视}item_category:平板电视");
        query.addFacetQuery("item_brand:三星");
        query.addFacetQuery("item_price:[1000 TO 2000]");

        // 获取查询结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析facet结果
        Map<String, Integer> facetQuery = response.getFacetQuery();
        Set<Map.Entry<String, Integer>> entries = facetQuery.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * facet range
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testFacetRange() throws SolrServerException, IOException {
        // q=*:*
        // &facet=on &
        // facet.range=item_price &
        // facet.range.start=0
        // &facet.range.end=20000
        // &facet.range.gap=2000
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFacet(true);
        query.addNumericRangeFacet("item_price", 0, 20000, 2000);
        query.set("facet.range.other", "all");

        // 获取查询结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析facet结果
        List<RangeFacet> facetRanges = response.getFacetRanges();
        RangeFacet rangeFacet = facetRanges.get(0);
        List<RangeFacet.Count> counts = rangeFacet.getCounts();
        System.out.println("before:" + rangeFacet.getBefore());
        System.out.println("after:" + rangeFacet.getAfter());
        System.out.println("between:" + rangeFacet.getBetween());
        for (RangeFacet.Count count : counts) {
            System.out.println(count.getValue() + ":" + count.getCount());
        }
    }

    /**
     * facet range Date
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testFacetRangeDate() throws SolrServerException, IOException, ParseException {
        // 设置中国时区
        TimeZone timeZone = TimeZone.getTimeZone("GMT +8");
        TimeZone.setDefault(timeZone);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // q=*:*& facet=on
        // &facet.range=item_updatetime
        // &facet.range.start=2015-01-01T00:00:00Z
        // &facet.range.end=2016-01-01T00:00:00Z
        // &facet.range.gap=%2B3MONTH
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFacet(true);
        Date start = simpleDateFormat.parse("2015-01-01 00:00:00");
        Date end = simpleDateFormat.parse("2016-01-01 00:00:00");
        query.addDateRangeFacet("item_updatetime", start, end, "+3MONTH");
        query.set("facet.range.other", "all");

        // 获取查询结果
        QueryResponse response = httpSolrClient.query(query);

        // 解析facet结果
        List<RangeFacet> facetRanges = response.getFacetRanges();
        RangeFacet rangeFacet = facetRanges.get(0);
        List<RangeFacet.Count> counts = rangeFacet.getCounts();
        System.out.println("before:" + rangeFacet.getBefore());
        System.out.println("after:" + rangeFacet.getAfter());
        System.out.println("between:" + rangeFacet.getBetween());
        for (RangeFacet.Count count : counts) {
            System.out.println(count.getValue() + ":" + count.getCount());
        }
    }

    /**
     * facet interval
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testIntervalFacet() throws SolrServerException, IOException {
        // 查询条件
        // q=*:*
        // &facet=true
        // &facet.interval=item_price
        // &f.item_price.facet.interval.set=[0,1000]
        // &f.item_price.facet.interval.set=[0,100]
        // &facet.interval=item_createtime
        // &f.item_createtime.facet.interval.set=[2019-01-01T0:0:0Z,NOW]
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFacet(true);
        query.addIntervalFacets("item_price", new String[]{"[0,1000]", "[0,100]"});
        query.addIntervalFacets("item_updatetime", new String[]{"[2015-01-01T00:00:00Z,NOW]"});

        QueryResponse response = httpSolrClient.query(query);

        //"facet_intervals":{
        //      "item_price":{
        //        "[0,1000]":285,
        //        "[0,100]":20},
        //      "item_createtime":{
        //        "[2014-01-01T0:0:0Z,NOW]":0}}
        List<IntervalFacet> intervalFacets = response.getIntervalFacets();
        for (IntervalFacet intervalFacet : intervalFacets) {
            System.out.println(intervalFacet.getField());
            List<IntervalFacet.Count> counts = intervalFacet.getIntervals();
            for (IntervalFacet.Count count : counts) {
                System.out.println(count.getKey() + ":" + count.getCount());
            }
        }
    }

    /**
     * facet pivot
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testFacetPivot() throws SolrServerException, IOException {
        // q=*:*&facet=on&facet.pivot=item_brand,item_category
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFacet(true);
        query.addFacetPivotField("item_brand,item_category");

        QueryResponse response = httpSolrClient.query(query);
        // "facet_pivot":{
        //      "item_brand,item_category":[{
        //          "field":"item_brand",
        //          "value":"三星",
        //          "count":154,
        //          "pivot":[{
        //              "field":"item_category",
        //              "value":"手机",
        //              "count":134},
        //            {
        //              "field":"item_category",
        //              "value":"平板电视",
        //              "count":13},
        //            {
        //              "field":"item_category",
        //              "value":"净化器",
        //              "count":4},
        //            {
        //              "field":"item_category",
        //              "value":"音乐",
        //              "count":3}]},

        NamedList<List<PivotField>> facetPivots = response.getFacetPivot();
        //List<PivotField> pivotFields = facetPivots.get("item_brand,item_category");

        for (Map.Entry<String, List<PivotField>> facetPivot : facetPivots) {
            System.out.println(facetPivot.getKey());
            List<PivotField> pivotFields = facetPivot.getValue();
            for (PivotField pivotField : pivotFields) {
                System.out.println("-----" + pivotField.getField() + ":" + pivotField.getValue() + ":" + pivotField.getCount() + "-----");
                List<PivotField> pivot = pivotField.getPivot();
                for (PivotField field : pivot) {
                    System.out.println(field.getField() + ":" + field.getValue() + ":" + field.getCount());
                }
                System.out.println("");
            }
        }
    }

    /**
     * group
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testGroup() throws SolrServerException, IOException {
        // q=item_title:手机&group=true&group.field=item_brand
        // &start=0&rows=3 &group.limit=5&group.offset=0
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手机");
        query.set(GroupParams.GROUP, true);
        query.set(GroupParams.GROUP_FIELD, "item_brand");

        // 分页：
        // 使用start和rows可以设置组的分页，使用group.limit和group.offset设置组内文档分页。
        // 组分页
        query.setStart(0);
        query.setRows(3);
        // 组内数据分页
        query.set(GroupParams.GROUP_OFFSET, 0);
        query.set(GroupParams.GROUP_LIMIT, 5);

        // group排序分为组排序，组内文档排序；对应的参数为sort和group.sort
        query.set(GroupParams.GROUP_SORT, "item_price asc");

        QueryResponse response = httpSolrClient.query(query);
        /**
         * {
         *   "responseHeader":{
         *     "status":0,
         *     "QTime":2,
         *     "params":{
         *       "q":"item_title:手机",
         *       "group.field":"item_brand",
         *       "group":"true"}},
         *   "grouped":{
         *     "item_brand":{
         *       "matches":716,
         *       "groups":[{
         *           "groupValue":"TCL",
         *           "doclist":{"numFound":19,"start":0,"numFoundExact":true,"docs":[
         *               {
         *                 "item_images":"http://img14.360buyimg.com/n1/s450x450_jfs/t3532/159/131329856/208385/d2e05067/58004df9Ncaaf71cc.jpg",
         *                 "item_updatetime":"2015-03-08T13:27:54Z",
         *                 "item_createtime":"2015-03-08T13:27:54Z",
         *                 "item_price":199.0,
         *                 "id":"1027857",
         *                 "item_title":"TCL 老人手机 (i310) 纯净白 移动联通2G手机",
         *                 "item_category":"手机",
         *                 "item_brand":"TCL",
         *                 "_version_":1701991317200961536}]
         *           }},
         */
        GroupResponse groupResponse = response.getGroupResponse();
        // 由于分组的字段可以是多个。所以返回数组
        List<GroupCommand> groupCommands = groupResponse.getValues();
        // 只有 item_brand 一个分组
        GroupCommand groupCommand = groupCommands.get(0);
        // 匹配到的文档数量
        System.out.println("匹配到的文档数量:" + groupCommand.getMatches());
        // 每个组合每个组中的文档信息
        List<Group> groups = groupCommand.getValues();
        for (Group group : groups) {
            SolrDocumentList documents = group.getResult();
            // TCL:19
            System.out.println(group.getGroupValue() + ":" + documents.getNumFound());
            for (SolrDocument document : documents) {
                System.out.println(document);
            }
        }
    }

    /**
     * highlight: 高亮查询,默认或fastVector
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testHighlight() throws SolrServerException, IOException {
        /**
         * // 默认高亮
         * q=item_title:手机
         * &hl=true
         * &hl.fl=item_title
         * &hl.simple.pre=<font>
         * &h1.simple.post=</font>
         */
        /*SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手机");
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<font>");
        query.setHighlightSimplePost("</font>");*/

        /**
         * // fastVector高亮
         * q=item_title:手机
         * &hl=true
         * &hl.fl=item_title
         * &hl.method=fastVector
         */
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手机");
        query.addHighlightField("item_title");
        query.set("hl.method", "fastVector");

        QueryResponse response = httpSolrClient.query(query);

        // 文档数据
        SolrDocumentList results = response.getResults();
        System.out.println("文档数量:" + results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println(result);
        }

        // 高亮解析
        /**
         *  "highlighting":{
         *     "1027857":{
         *       "item_title":["TCL 老人<font>手机</em> (i310) 纯净白 移动联通2G<font>手机</em>"]},
         */
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (Map.Entry<String, Map<String, List<String>>> entry : highlighting.entrySet()) {
            System.out.println("数据id:" + entry.getKey());
            Map<String, List<String>> value = entry.getValue();
            for (Map.Entry<String, List<String>> listEntry : value.entrySet()) {
                System.out.println(listEntry.getValue().get(0));
            }
        }
    }

    /**
     * spellcheck:拼写检查
     *
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testSpellCheck() throws SolrServerException, IOException {
        /**
         * q=item_title:iphona
         * &spellcheck=true
         */
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:ipona");
        query.set("spellcheck", "true");

        QueryResponse response = httpSolrClient.query(query);

        /**
         * 解析拼写检查
         * "spellcheck":{
         *     "suggestions":[
         *       "iphona",{
         *         "numFound":1,
         *         "startOffset":11,
         *         "endOffset":17,
         *         "suggestion":["iphone"]}]}}
         */
        SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();

        // key 为建议的词，及 “iphona”
        Map<String, SpellCheckResponse.Suggestion> suggestionMap = spellCheckResponse.getSuggestionMap();
        for (Map.Entry<String, SpellCheckResponse.Suggestion> suggestionEntry : suggestionMap.entrySet()) {
            System.out.println(suggestionEntry.getKey());
            SpellCheckResponse.Suggestion suggestionEntryValue = suggestionEntry.getValue();
            System.out.println("给出正确建议词数量:" + suggestionEntryValue.getNumFound());
            for (String alternative : suggestionEntryValue.getAlternatives()) {
                System.out.println(alternative);
            }
        }
    }

    /**
     * auto suggest:自动建议
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void testAutoSuggest() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery("华为");
        query.set("suggest", "true");
        query.set("suggest.dicetionary", "suggest");
        query.set("suggest.count", "5");

        QueryResponse response = httpSolrClient.query(query);
        /**
         * "suggest":{"default":{
         *       "华为":{
         *         "numFound":5,
         *         "suggestions":[{
         *             "term":"华为 Ascend Mate7 曜石黑 移动4G手机 双卡双待双通",
         *             "weight":3399,
         *             "payload":""},
         */
        SuggesterResponse suggesterResponse = response.getSuggesterResponse();
        Map<String, List<Suggestion>> suggestions = suggesterResponse.getSuggestions();
        List<Suggestion> suggest = suggestions.get("default");
        for (Suggestion suggestion : suggest) {
            System.out.println(suggestion.getTerm());
        }
    }
}
