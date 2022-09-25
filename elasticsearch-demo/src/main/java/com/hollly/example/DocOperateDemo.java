package com.hollly.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Date;

/**
 *
 * 7.8文档地址
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.8/java-rest-high-document-index.html
 *
 * @author hollly
 * @date 2022/2/5 23:10
 */
public class DocOperateDemo {

    public static void main(String[] args) throws IOException {
        add();
    }

    public static void add() throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        IndexRequest indexRequest = new IndexRequest("twitter")
                .id("1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }


    public static void search() throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        SearchRequest searchRequest = new SearchRequest();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));


        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);

        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//        searchSourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));

        searchRequest.indices("posts");
        searchRequest.source(searchSourceBuilder);

    }
}
