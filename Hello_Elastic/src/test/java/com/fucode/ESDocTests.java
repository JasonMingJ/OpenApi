package com.fucode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fucode.entity.Human;
import com.fucode.utils.ESClient;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ESDocTests
 * @Description: TODO
 * @Author user
 * @Date 2020/9/22
 * @Version V1.0
 **/
public class ESDocTests {

    ObjectMapper objectMapper = new ObjectMapper();
    RestHighLevelClient client = ESClient.getClient();
    String index = "human";
    String type = "man";

    @Test
    public void creatDoc() throws IOException {
        //准备一JSON数据
        Human human = new Human(2,19,"李四",new Date());
        String json = objectMapper.writeValueAsString(human);

        //配置一个request
        IndexRequest indexRequest = new IndexRequest(index, type, human.getId().toString());
        indexRequest.source(json, XContentType.JSON);

        //通过client对象执行添加doc
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        //输出返回的结果
        System.out.println(indexResponse);
    }

    @Test
    public void update() throws IOException {
        //创建一个map指定需要修改的内容
        Map<String,Object> doc = new HashMap<>();
        doc.put("name","钻石王老五");
        String docId = "1";

        //创建request对象
        UpdateRequest updateRequest = new UpdateRequest(index, type, docId);
        updateRequest.doc(doc);

        //通过client执行doc更新
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(updateResponse);
    }

    @Test
    public void deldoc() throws IOException {
        //配置request对象
        DeleteRequest deleteRequest = new DeleteRequest(index, type, "2");
        //用client执行删除doc
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(deleteRequest);
    }

    @Test
    public void bulkCreateDoc() throws IOException {
        Human h1 = new Human(2,20,"刘二",new Date());
        Human h2 = new Human(3,19,"王二狗",new Date());
        Human h3 = new Human(4,25,"张老三",new Date());

        String json1 = objectMapper.writeValueAsString(h1);
        String json2 = objectMapper.writeValueAsString(h2);
        String json3 = objectMapper.writeValueAsString(h3);

        //创建BulkRequest，装入对象们
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(index,type,h1.getId().toString()).source(json1, XContentType.JSON));
        bulkRequest.add(new IndexRequest(index,type,h2.getId().toString()).source(json2, XContentType.JSON));
        bulkRequest.add(new IndexRequest(index,type,h3.getId().toString()).source(json3, XContentType.JSON));

        //client执行插入
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk);
    }

    @Test

    public void bulkDel() throws IOException {

        //封装Request对象
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new DeleteRequest(index,type,"2"));
        bulkRequest.add(new DeleteRequest(index,type,"3"));
        bulkRequest.add(new DeleteRequest(index,type,"4"));
        //client执行
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(bulk);
    }
}
