package com.fucode.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName SmsLogs
 * @Description: TODO
 * @Author user
 * @Date 2020/9/23
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsLogs {
    private String id;                      //唯一id
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;                //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sendDate;                  //发送时间
    private String longCode;                //发送的长号码
    private String mobile;                  //下发手机号
    private String corpName;                //下发的公司名称，需要分词
    private String smsContent;              //短信内容，需要分词
    private Integer state;                  //短信下发状态，0成功，1失败
    private Integer operatorId;             //运营商编号，1移动，2联通，3电信
    private String province;                //省份
    private String ipAddr;                   //下发的ip地址
    private Integer replyTotal;             //短信状态报告返回时长（秒）
    private Integer fee;                    //费用
}
