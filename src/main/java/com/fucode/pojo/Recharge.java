package com.fucode.pojo;

import java.util.Date;

public class Recharge {

  private long id;
  private long cusId;
  private long orderId;
  private Date createtime;
  private Date updatetime;
  private long money;
  private long state;
  private long paymenttype;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCusId() {
    return cusId;
  }

  public void setCusId(long cusId) {
    this.cusId = cusId;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
    this.updatetime = updatetime;
  }

  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

  public long getPaymenttype() {
    return paymenttype;
  }

  public void setPaymenttype(long paymenttype) {
    this.paymenttype = paymenttype;
  }
}