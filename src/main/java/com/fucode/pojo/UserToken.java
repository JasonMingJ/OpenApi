package com.fucode.pojo;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class UserToken {

  private long id;
  private long userId;
  private String accessToken;
  @JSONField(format = "yyyy-MM-dd")
  private Date expireTime;
  @JSONField(format = "yyyy-MM-dd")
  private Date startTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Date getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Date expireTime) {
    this.expireTime = expireTime;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
}
