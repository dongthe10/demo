package com.hollly.example;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;

/**
 * @author hollly
 * @date 2022/2/5 17:50
 */
public class Main {

    public static void main(String[] args) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        CreateIndexRequest request = new CreateIndexRequest("twitter");


    }


}
