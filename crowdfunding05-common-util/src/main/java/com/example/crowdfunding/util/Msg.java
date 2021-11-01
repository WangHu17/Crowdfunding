package com.example.crowdfunding.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghu
 * @discrption： 后端返回数据统一格式，前端ajax接收
 * @create 2021-07-08 20:07
 */
public class Msg {

    private Integer code;
    private String msg;
    private Map<String,Object> data = new HashMap<>();
    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("处理成功");
        return msg;
    }
    public static Msg successWithMsg(String successMsg){
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg(successMsg);
        return msg;
    }
    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("处理失败");
        return msg;
    }
    public static Msg failWithMsg(String errorMsg){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg(errorMsg);
        return msg;
    }
    public Msg add(String key,Object value){
        this.getData().put(key,value);
        return this;
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
