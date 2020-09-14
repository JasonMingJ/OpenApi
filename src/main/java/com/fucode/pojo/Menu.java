package com.fucode.pojo;


import java.util.ArrayList;
import java.util.List;

public class Menu {

  private Integer id;
  private String name;
  private Integer parentId;
  private String url;
  private String icon;
  private String perms;
  private Long type;
  private Long sort;
  private List<Menu> childrenMenuList = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }

  public List<Menu> getChildrenMenuList() {
    return childrenMenuList;
  }

  public void setChildrenMenuList(List<Menu> childrenMenuList) {
    this.childrenMenuList = childrenMenuList;
  }
}
