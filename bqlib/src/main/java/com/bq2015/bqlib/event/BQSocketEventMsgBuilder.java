/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqlib.event;

import com.bq2015.bqhttp.event.BQNET_STATUS;

public class BQSocketEventMsgBuilder {
    private Object context;
    private int what;
    private int arg1;
    private int arg2;
    private Object obj;
    private String repMsg;
    private int whatCommonResponse;
    private BQNET_STATUS netStatus;

    public BQSocketEventMsgBuilder context(Object context) {
        this.context = context;
        return this;
    }

    public BQSocketEventMsgBuilder what(int what) {
        this.what = what;
        return this;
    }

    public BQSocketEventMsgBuilder arg1(int arg1) {
        this.arg1 = arg1;
        return this;
    }

    public BQSocketEventMsgBuilder arg2(int arg2) {
        this.arg2 = arg2;
        return this;
    }

    public BQSocketEventMsgBuilder obj(Object obj) {
        this.obj = obj;
        return this;
    }

    public BQSocketEventMsgBuilder repMsg(String repMsg) {
        this.repMsg = repMsg;
        return this;
    }

    public BQSocketEventMsgBuilder whatCommonResponse(int whatCommonResponse) {
        this.whatCommonResponse = whatCommonResponse;
        return this;
    }

    public BQSocketEventMsgBuilder netStatus(BQNET_STATUS netStatus) {
        this.netStatus = netStatus;
        return this;
    }

    public BQSocketEventMsg createYDSocketEventMsg() {
        return new BQSocketEventMsg(context, what, arg1, arg2, obj, repMsg, whatCommonResponse, netStatus);
    }
}