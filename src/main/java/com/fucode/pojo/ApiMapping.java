package com.fucode.pojo;


public class ApiMapping {

  private Long id;
  private String gatewayApiName;
  private String insideApiUrl;
  private Integer state;
  private String description;
  private String serviceId;
  private Integer idempotents;
  private Integer needfee;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGatewayApiName() {
    return gatewayApiName;
  }

  public void setGatewayApiName(String gatewayApiName) {
    this.gatewayApiName = gatewayApiName;
  }

  public String getInsideApiUrl() {
    return insideApiUrl;
  }

  public void setInsideApiUrl(String insideApiUrl) {
    this.insideApiUrl = insideApiUrl;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public Integer getIdempotents() {
    return idempotents;
  }

  public void setIdempotents(Integer idempotents) {
    this.idempotents = idempotents;
  }

  public Integer getNeedfee() {
    return needfee;
  }

  public void setNeedfee(Integer needfee) {
    this.needfee = needfee;
  }
}
