package com.hollly.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;

/**
 * @author hollly
 * @date 2022/2/5 22:12
 */
public class IndexOperateDemo {

    public static void main(String[] args) throws IOException {
        create();
    }

    public static void create() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest("twitter");

        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );

        // mapping 可以提供两个类型参数 string 和 map，XContentBuilder
        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"message\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);

        // 设置别名
        request.alias(new Alias("twitter_alias").filter(QueryBuilders.termQuery("user", "kimchy")));

        // 同步执行
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

//        ActionListener<CreateIndexResponse> listener =
//                new ActionListener<CreateIndexResponse>() {
//
//                    @Override
//                    public void onResponse(CreateIndexResponse createIndexResponse) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//
//                    }
//                };
//        client.indices().createAsync(request, RequestOptions.DEFAULT, listener);
        // 异步执行

        // 返回结果
        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
        System.out.println("acknowledged: " + acknowledged);
        System.out.println("shardsAcknowledged: " + shardsAcknowledged);
    }

    public static void update() {

    }

    public static void delete() throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        DeleteIndexRequest request = new DeleteIndexRequest("twitter");

        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");

        AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
    }

}
