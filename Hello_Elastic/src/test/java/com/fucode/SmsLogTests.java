package com.fucode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fucode.entity.SmsLogs;
import com.fucode.utils.ESClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName SmsLogTests
 * @Description: 测试
 * @Author user
 * @Date 2020/9/23
 * @Version V1.0
 **/
public class SmsLogTests {

    static RestHighLevelClient restHighLevelClient =  ESClient.getClient();
    static ObjectMapper objectMapper = new ObjectMapper();


    private static final String index = "sms_logs_index";
    private static final String type = "sms_logs_type";

    SearchRequest searchRequest = new SearchRequest(index);

    @Test
    public void createIndex() throws IOException {
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards", 3)
                .put("number_of_replicas", 1);

        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("createDate")
                            .field("type", "date")
                            .field("format","yyyy-MM-dd")
                        .endObject()
                        .startObject("sendDate")
                            .field("type", "date")
                            .field("format","yyyy-MM-dd")
                        .endObject()
                        .startObject("longCode")
                            .field("type","keyword")
                        .endObject()
                        .startObject("mobile")
                            .field("type","keyword")
                        .endObject()
                        .startObject("corpName")
                            .field("type","keyword")
                        .endObject()
                        .startObject("smsContent")
                            .field("type","text")
                            .field("analyzer","ik_max_word")
                        .endObject()
                        .startObject("state")
                            .field("type","integer")
                        .endObject()
                        .startObject("operatorId")
                            .field("type","integer")
                        .endObject()
                        .startObject("province")
                            .field("type","keyword")
                        .endObject()
                        .startObject("ipAddr")
                            .field("type","ip")
                        .endObject()
                        .startObject("replyTotal")
                            .field("type","integer")
                        .endObject()
                        .startObject("fee")
                            .field("type","integer")
                        .endObject()
                    .endObject()
                .endObject();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type,mappings);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    @Test
    public void testBulkData() throws IOException {
        //创建request对象
        BulkRequest request = new BulkRequest();
        //定义smsLogs对象，并设置数据
        SmsLogs smsLogs = new SmsLogs();
        smsLogs.setMobile("13800000000");
        smsLogs.setCorpName("途虎养车");
        smsLogs.setCreateDate(new Date());
        smsLogs.setSendDate(new Date());
        smsLogs.setIpAddr("10.126.2.9");
        smsLogs.setLongCode("10690000988");
        smsLogs.setReplyTotal(10);
        smsLogs.setState(0);
        smsLogs.setSmsContent("【途虎养车】亲爱的张三先生/女士，您在途虎购买的货品(单号TH123456)已 到指定安装店多日，" + "现需与您确认订单的安装情况，请点击链接按实际情况选择（此链接有效期为72H）。您也可以登录途 虎APP进入" + "“我的-待安装订单”进行预约安装。若您在服务过程中有任何疑问，请致电400-111-8868向途虎咨 询。");
        smsLogs.setProvince("北京");
        smsLogs.setOperatorId(1);
        smsLogs.setFee(3);
        //client执行操作
        request.add(new IndexRequest(index,type,"21").source(objectMapper.writeValueAsString(smsLogs), XContentType.JSON));

        smsLogs.setMobile("13700000001");
        smsLogs.setProvince("上海");
        smsLogs.setSmsContent("【途虎养车】亲爱的刘红先生/女士，您在途虎购买的货品(单号TH1234526)已 到指定安装店多日，" + "现需与您确认订单的安装情况，请点击链接按实际情况选择（此链接有效期为72H）。您也可以登录途 虎APP进入" + "“我的-待安装订单”进行预约安装。若您在服务过程中有任何疑问，请致电400-111-8868向途虎咨 询。");
        request.add(new IndexRequest(index, type, "22").source(objectMapper.writeValueAsString(smsLogs), XContentType.JSON));

        SmsLogs smsLogs1 = new SmsLogs();
        smsLogs1.setMobile("13100000000");
        smsLogs1.setCorpName("盒马鲜生");
        smsLogs1.setCreateDate(new Date());
        smsLogs1.setSendDate(new Date());
        smsLogs1.setIpAddr("10.126.2.9");
        smsLogs1.setLongCode("10660000988");
        smsLogs1.setReplyTotal(15);
        smsLogs1.setState(0);
        smsLogs1.setSmsContent("【盒马】您尾号12345678的订单已开始配送，请在您指定的时间收货不要走开 哦~配送员：" + "刘三，电话：13800000000");
        smsLogs1.setProvince("北京");
        smsLogs1.setOperatorId(2);
        smsLogs1.setFee(5);
        request.add(new IndexRequest(index, type, "23").source(objectMapper.writeValueAsString(smsLogs1), XContentType.JSON));

        smsLogs1.setMobile("18600000001");
        smsLogs1.setProvince("上海");
        smsLogs1.setSmsContent("【盒马】您尾号7775678的订单已开始配送，请在您指定的时间收货不要走开 哦~配送员：" + "王五，电话：13800000001");
        request.add(new IndexRequest(index, type, "24").source(objectMapper.writeValueAsString(smsLogs1), XContentType.JSON));

        // -------------------------------------------------------------------------------------------------------------------

        SmsLogs smsLogs2 = new SmsLogs();
        smsLogs2.setMobile("15300000000");
        smsLogs2.setCorpName("滴滴打车");
        smsLogs2.setCreateDate(new Date());
        smsLogs2.setSendDate(new Date());
        smsLogs2.setIpAddr("10.126.2.8");
        smsLogs2.setLongCode("10660000988");
        smsLogs2.setReplyTotal(50);
        smsLogs2.setState(1);
        smsLogs2.setSmsContent("【滴滴单车平台】专属限时福利！青桔/小蓝月卡立享5折，特惠畅骑30天。" + "戳 https://xxxxxx退订TD");
        smsLogs2.setProvince("上海");
        smsLogs2.setOperatorId(3);
        smsLogs2.setFee(7);
        request.add(new IndexRequest(index, type, "25").source(objectMapper.writeValueAsString(smsLogs2), XContentType.JSON));

        smsLogs2.setMobile("18000000001");
        smsLogs2.setProvince("武汉");
        smsLogs2.setSmsContent("【滴滴单车平台】专属限时福利！青桔/小蓝月卡立享5折，特惠畅骑30天。" + "戳 https://xxxxxx退订TD");
        request.add(new IndexRequest(index, type, "26").source(objectMapper.writeValueAsString(smsLogs2), XContentType.JSON));


        // -------------------------------------------------------------------------------------------------------------------

        SmsLogs smsLogs3 = new SmsLogs();
        smsLogs3.setMobile("13900000000");
        smsLogs3.setCorpName("招商银行");
        smsLogs3.setCreateDate(new Date());
        smsLogs3.setSendDate(new Date());
        smsLogs3.setIpAddr("10.126.2.8");
        smsLogs3.setLongCode("10690000988");
        smsLogs3.setReplyTotal(50);
        smsLogs3.setState(0);
        smsLogs3.setSmsContent("【招商银行】尊贵的李四先生,恭喜您获得华为P30 Pro抽奖资格,还可领100 元打" + "车红包,仅限1天");
        smsLogs3.setProvince("上海");
        smsLogs3.setOperatorId(1);
        smsLogs3.setFee(8);
        request.add(new IndexRequest(index, type, "27").source(objectMapper.writeValueAsString(smsLogs3), XContentType.JSON));

        smsLogs3.setMobile("13990000001");
        smsLogs3.setProvince("武汉");
        smsLogs3.setSmsContent("【招商银行】尊贵的李四先生,恭喜您获得华为P30 Pro抽奖资格,还可领100 元打" + "车红包,仅限1天");
        request.add(new IndexRequest(index, type, "28").source(objectMapper.writeValueAsString(smsLogs3), XContentType.JSON));

        // -------------------------------------------------------------------------------------------------------------------

        SmsLogs smsLogs4 = new SmsLogs();
        smsLogs4.setMobile("13700000000");
        smsLogs4.setCorpName("中国平安保险有限公司");
        smsLogs4.setCreateDate(new Date());
        smsLogs4.setSendDate(new Date());
        smsLogs4.setIpAddr("10.126.2.8");
        smsLogs4.setLongCode("10690000998");
        smsLogs4.setReplyTotal(18);
        smsLogs4.setState(0);
        smsLogs4.setSmsContent("【中国平安】奋斗的时代，更需要健康的身体。中国平安为您提供多重健康保 障，在奋斗之路上为您保驾护航。退订请回复TD");
        smsLogs4.setProvince("武汉");
        smsLogs4.setOperatorId(1);
        smsLogs4.setFee(5);
        request.add(new IndexRequest(index, type, "29").source(objectMapper.writeValueAsString(smsLogs4), XContentType.JSON));

        smsLogs4.setMobile("13990000002");
        smsLogs4.setProvince("武汉");
        smsLogs4.setSmsContent("【招商银行】尊贵的王五先生,恭喜您获得iphone 56抽奖资格,还可领5 元打" + "车红包,仅限100天");
        request.add(new IndexRequest(index, type, "30").source(objectMapper.writeValueAsString(smsLogs4), XContentType.JSON));

        // -------------------------------------------------------------------------------------------------------------------


        SmsLogs smsLogs5 = new SmsLogs();
        smsLogs5.setMobile("13600000000");
        smsLogs5.setCorpName("中国移动");
        smsLogs5.setCreateDate(new Date());
        smsLogs5.setSendDate(new Date());
        smsLogs5.setIpAddr("10.126.2.8");
        smsLogs5.setLongCode("10650000998");
        smsLogs5.setReplyTotal(60);
        smsLogs5.setState(0);
        smsLogs5.setSmsContent("【北京移动】尊敬的客户137****0000，5月话费账单已送达您的139邮箱，" + "点击查看账单详情 http://y.10086.cn/; " + " 回Q关闭通知，关注“中国移动139邮箱”微信随时查账单【中国移动 139邮箱】");
        smsLogs5.setProvince("武汉");
        smsLogs5.setOperatorId(1);
        smsLogs5.setFee(4);
        request.add(new IndexRequest(index, type, "31").source(objectMapper.writeValueAsString(smsLogs5), XContentType.JSON));

        smsLogs5.setMobile("13990001234");
        smsLogs5.setProvince("山西");
        smsLogs5.setSmsContent("【北京移动】尊敬的客户137****1234，8月话费账单已送达您的126邮箱，\" + \"点击查看账单详情 http://y.10086.cn/; \" + \" 回Q关闭通知，关注“中国移动126邮箱”微信随时查账单【中国移动 126邮箱】");
        request.add(new IndexRequest(index, type, "32").source(objectMapper.writeValueAsString(smsLogs5), XContentType.JSON));
        // -------------------------------------------------------------------------------------------------------------------

        BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(bulk.toString());
    }

    @Test
    public void termQ() throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(QueryBuilders.termQuery("province", "北京"));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //source中的数据
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    @Test
    public void termsQ() throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("province", "北京","山西"));

        searchRequest.source(builder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    @Test
    public void matchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //ES默认只查询10条数据，如果想查询更多就要自定义size
        searchSourceBuilder.size(20);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    @Test
    public void matchField() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder sourceBuilder = searchSourceBuilder.query(QueryBuilders.matchQuery("smsContent", "收货安装"));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    @Test
    public void boolMatch() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder sourceBuilder = searchSourceBuilder.query(QueryBuilders.matchQuery("smsContent", "中国 健康")
                .operator(Operator.AND));

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    @Test
    public void multiFieldMatch() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("北京", "province","smsContent"));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    @Test
    public void findById() throws IOException {
        GetRequest getRequest = new GetRequest(index, type, "24");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsMap());
    }

    @Test
    public void getByIds() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("23","24","25"));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

    }

    @Test
    public void predix() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.prefixQuery("corpName","盒马"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void fuzzy() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("corpName", "盒马先生").prefixLength(2));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

    }

    @Test
    public void wildcard() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wildcardQuery("corpName", "中国*"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void range() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("fee").lte(10).gte(5));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void regex() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.regexpQuery("mobile", "180[0-9]{8}"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void scroll() throws IOException {
        searchRequest.types(type);

        //指定scroll信息
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(3);
        searchSourceBuilder.sort("fee", SortOrder.DESC);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        String scrollId = searchResponse.getScrollId();
        System.out.println("----------首页---------");
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

        while (true){
            //循环-创建SearchScrollRequest
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            //指定scroll生存时间
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(1L));
            //执行查询并返回结果
            SearchResponse scrollResp = restHighLevelClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);

            //判断是否查到了数据，然后输出
            SearchHit[] hits = scrollResp.getHits().getHits();
            if (hits!=null&& hits.length>0){
                System.out.println("----------下一页---------");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsMap());
                }
            }else{
                //判断没有查询到数据-退出循环
                System.out.println("----------结束---------");
                break;
            }
        }

        //创建clearScrollRequest
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        System.out.println("删除Scroll的结果："+clearScrollResponse.isSucceeded());
    }

    @Test
    public void delByQ() throws IOException {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(index);

        deleteByQueryRequest.types(type);

        deleteByQueryRequest.setQuery(QueryBuilders.rangeQuery("fee").lt(5));

        BulkByScrollResponse bulkByScrollResponse = restHighLevelClient
                .deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        System.out.println(bulkByScrollResponse.toString());
    }

    @Test
    public void bool() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        boolQueryBuilder.should(QueryBuilders.termQuery("province", "北京"));
        boolQueryBuilder.should(QueryBuilders.termQuery("province", "武汉"));

        boolQueryBuilder.mustNot(QueryBuilders.termQuery("operatorId", 2));

        boolQueryBuilder.must(QueryBuilders.matchQuery("smsContent", "中国"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("smsContent", "平安"));

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void boosting() throws IOException {
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoostingQueryBuilder boostingQueryBuilder = QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("smsContent", "收货安装"),
                QueryBuilders.matchQuery("smsContent", "王五"))
                .negativeBoost(0.5f);
        searchSourceBuilder.query(boostingQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
