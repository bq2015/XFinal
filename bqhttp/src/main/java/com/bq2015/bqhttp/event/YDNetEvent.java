package com.bq2015.bqhttp.event;

import com.bq2015.oknet.callback.NetEvent;

import com.bq2015.bqhttp.net.NetRequest;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/5/31
 ************************************************************/
public class YDNetEvent extends NetEvent{

    public static final int WHAT_NO_USE = -1;

    public Object context;
    public Object what;
    public int arg1;
    public int arg2;
    public Object obj;
    public String repMsg;

    public YDNET_STATUS netStatus;

    public YDNetEvent() {

    }

    public YDNetEvent(Object what, int arg1, int arg2, Object obj,
                      YDNET_STATUS ydnet_STATUS,
                      Object context, String repMsg) {
        this.what = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj = obj;
        netStatus = ydnet_STATUS;
        this.context = context;
        this.repMsg = repMsg;
    }

    public boolean whatEqual(NetRequest request){
        if(request == null){
            return false;
        }
        return what.equals(request);
    }

    public boolean requestOK(){
        if(YDNET_STATUS.OK.equals(netStatus)){
            return true;
        }
        return false;
    }

    public <T> T getNetResult(){
        return (T)obj;
    }

    @Override
    public String toString() {
        return "YDNetEvent{" +
                "context=" + context +
                ", what=" + what +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", obj=" + obj +
                ", repMsg='" + repMsg + '\'' +
                ", netStatus=" + netStatus +
                '}';
    }
}
