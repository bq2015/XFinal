package com.bq2015.bqhttp.event;

import com.bq2015.bqhttp.net.NetRequest;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/5/31
 ************************************************************/
public class BQNetEvent {

    public static final int WHAT_NO_USE = -1;

    public Object context;
    public Object who;
    public int arg1;
    public int arg2;
    public Object obj;
    public String repMsg;

    public BQNET_STATUS netStatus;

    public BQNetEvent() {

    }

    public BQNetEvent(Object what, int arg1, int arg2, Object obj,
                      BQNET_STATUS ydnet_STATUS,
                      Object context, String repMsg) {
        this.who = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj = obj;
        netStatus = ydnet_STATUS;
        this.context = context;
        this.repMsg = repMsg;
    }

    public boolean whoEqual(NetRequest request) {
        if (request == null) {
            return false;
        }
        return who.equals(request);
    }

    public boolean requestOK() {
        if (BQNET_STATUS.OK.equals(netStatus)) {
            return true;
        }
        return false;
    }

    public <T> T getNetResult() {
        return (T) obj;
    }

    @Override
    public String toString() {
        return "BQNetEvent{" +
                "context=" + context +
                ", who=" + who +
                ", code1=" + arg1 +
                ", code2=" + arg2 +
                ", obj=" + obj +
                ", repMsg='" + repMsg + '\'' +
                ", netStatus=" + netStatus +
                '}';
    }
}
