package com.bq2015.bqhttp.event;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/5/31
 ************************************************************/
public class YDNetEventBuilder {

    private Object what;
    private int    arg1;
    private int    arg2;
    private Object context;
    private Object obj;
    private String repMsg;

    private YDNET_STATUS YDNETSTATUS;

    public YDNetEventBuilder repMsg(String repMsg){
        this.repMsg = repMsg;
        return this;
    }

    public YDNetEventBuilder what(Object what) {
        this.what = what;
        return this;
    }

    public YDNetEventBuilder arg1(int arg1) {
        this.arg1 = arg1;
        return this;
    }

    public YDNetEventBuilder arg2(int arg2) {
        this.arg2 = arg2;
        return this;
    }

    public YDNetEventBuilder obj(Object obj) {
        this.obj = obj;
        return this;
    }
    public YDNetEventBuilder context(Object context){
        this.context = context;
        return this;
    }

    public YDNetEventBuilder ydNetStatus(YDNET_STATUS YDNETSTATUS) {
        this.YDNETSTATUS = YDNETSTATUS;
        return this;
    }

    public YDNetEvent createYDNetEvent() {
        return new YDNetEvent(what, arg1, arg2, obj, YDNETSTATUS, context, repMsg);
    }
}
