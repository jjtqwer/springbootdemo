package com.example.springbootdemo.util;

import java.util.HashMap;

public class ResultData extends HashMap<String,Object> {

    public ResultData() {
        put("code",0);
        put("msg","success");
    }

    public static ResultData error(int code,String msg){
        ResultData r=new ResultData();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    public static ResultData error(String msg){
        return error(500,msg);
    }

    public static ResultData error(){
        return error(500,"未知异常");
    }

    public static ResultData success(){
        return new ResultData();
    }

    public static ResultData success(int code,String msg){
        ResultData r=new ResultData();
        r.put("code",code);
        r.put("msg",msg);
        return r;

    }

    public ResultData put(String key,Object val){
        super.put(key,val);
        return this;
    }

    public static void main(String[] args) {
        new String("abc");
    }
}
