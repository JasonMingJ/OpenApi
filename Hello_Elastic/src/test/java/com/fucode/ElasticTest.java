package com.fucode;

import com.fucode.utils.ESClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName ElasticTest
 * @Description: TODO
 * @Author user
 * @Date 2020/9/22
 * @Version V1.0
 **/
public class ElasticTest {

    private RestHighLevelClient client = ESClient.getClient();

    private static String index = "human";
    private static String type = "man";

    @Test
    public void creatIndex() throws IOException {

        //关于索引的Settings
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards",3)
                .put("number_of_replicas",1);

        //索引结构的mappings
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("name")
                            .field("type","text")
                        .endObject()
                        .startObject("age")
                            .field("type","integer")
                        .endObject()
                        .startObject("birthday")
                            .field("type","date")
                            .field("format","yyyy-MM-dddd")
                        .endObject()
                    .endObject()
                .endObject();

        //将Settings和Mapping封装到一个Requst对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type,mappings);
        //通过client对象连接ES并执行创建索引
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        //输出
        System.out.println("response:" + createIndexResponse.toString());

    }

    @Test
    public void exists() throws IOException {
        //配置request对象
        GetIndexRequest getIndexRequest = new GetIndexRequest();
        getIndexRequest.indices("person");

        //通过client操作查询index是否存在
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    @Test
    public void delete() throws IOException {
        //配置request对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices("person");

        //通过client对象执行删除
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());
    }

}
