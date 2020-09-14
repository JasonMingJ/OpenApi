package com.fucode.bean;

/**
 * @ClassName AjaxResponse
 * @Description: 主要用于给前端返回操作结果
 * @Author user
 * @Date 2020/9/7
 * @Version V1.0
 **/
public class AjaxResponse {
    private boolean status;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AjaxResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public AjaxResponse(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public AjaxResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
