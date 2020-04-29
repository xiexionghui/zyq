package com.ubo.zyq.entity;

/**
 * @author zyq
 * @date 2020-2-27 13:30
 */
public class LayuiMsg {
    private Integer code;
    private String msg;
    private Object data;

    public LayuiMsg() {
    }

    public LayuiMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayuiMsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
