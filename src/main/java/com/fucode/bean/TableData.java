package com.fucode.bean;

import java.util.List;

/**
 * @ClassName TableData
 * @Description: TODO
 * @Author user
 * @Date 2020/9/7
 * @Version V1.0
 **/
public class TableData<T>{
    private Integer code = 0;
    private String msg;
    private Long count;
    private List<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
