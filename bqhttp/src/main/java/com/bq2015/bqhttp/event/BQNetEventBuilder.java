package com.bq2015.bqhttp.event;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/5/31
 ************************************************************/
public class BQNetEventBuilder {

    private Object what;
    private int code1;
    private int code2;
    private Object context;
    private Object obj;
    private String repMsg;

    private BQNET_STATUS YDNETSTATUS;

    public BQNetEventBuilder repMsg(String repMsg) {
        this.repMsg = repMsg;
        return this;
    }

    public BQNetEventBuilder who(Object what) {
        this.what = what;
        return this;
    }

    public BQNetEventBuilder code1(int code1) {
        this.code1 = code1;
        return this;
    }

    public BQNetEventBuilder code2(int code2) {
        this.code2 = code2;
        return this;
    }

    public BQNetEventBuilder obj(Object obj) {
        this.obj = obj;
        return this;
    }

    public BQNetEventBuilder context(Object context) {
        this.context = context;
        return this;
    }

    public BQNetEventBuilder bqNetStatus(BQNET_STATUS YDNETSTATUS) {
        this.YDNETSTATUS = YDNETSTATUS;
        return this;
    }

    public BQNetEvent createBQNetEvent() {
        return new BQNetEvent(what, code1, code2, obj, YDNETSTATUS, context, repMsg);
    }
}
