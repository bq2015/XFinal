/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqhttp.bqnet;

/************************************************************
 * ,* Author:  bq2015
 * ,* Description:  返回给HttpCallBack的信息,此结构体因和后台协商定义，且因利用json库注入数据所以字段需要和协议字段匹配
 * ,* Date: 2016/2/22 20:54
 * ,
 ************************************************************/
public class BQNetInfo {

    private int code;//	状态码
    private String msg;
    private String data;
    private String mOrignJson;
    private int mMissField;

    public void setMissField(int missField) {
        mMissField = missField;
    }

    public int getMissField() {
        return mMissField;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrignJson() {
        return mOrignJson;
    }

    public void setOrignJson(String orignJson) {
        mOrignJson = orignJson;
    }

    @Override
    public String toString() {
        return "NetInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", mOrignJson='" + mOrignJson + '\'' +
                ", mMissField=" + mMissField +
                '}';
    }

}
