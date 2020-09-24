package com.fucode.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @ClassName ESClient
 * @Description: TODO
 * @Author user
 * @Date 2020/9/22
 * @Version V1.0
 **/
public class ESClient {

    public static RestHighLevelClient getClient(){

        //创建httphost对象
        HttpHost httpHost = new HttpHost("192.168.80.188", 9200);

        //创建RestClientBuilder
        RestClientBuilder builder = RestClient.builder(httpHost);

        //创建RestHighLevelClient并返回
        return new RestHighLevelClient(builder);
    }
}
