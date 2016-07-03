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

import android.util.Log;

import com.bq2015.bqhttp.event.BQNET_STATUS;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/3/31
 ************************************************************/
public class BQSocketEventMsg {

    public static final int WHAT_NO_USE = -1;

    public Object context;
    public int what;
    public int arg1;
    public int arg2;
    public Object obj;
    public String repMsg;
    public int whatCommonResponse;

    public BQNET_STATUS netStatus;

    public BQSocketEventMsg() {
    }

    public BQSocketEventMsg(Object context, int what, int arg1, int arg2, Object obj, String repMsg,
                            int whatCommonResponse, BQNET_STATUS netStatus) {
        this.context = context;
        this.what = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj = obj;
        this.repMsg = repMsg;
        this.whatCommonResponse = whatCommonResponse;
        this.netStatus = netStatus;
    }

    public boolean whatCommonResponseEqual(Class cls){
        if(cls == null) return false;
        return whatCommonResponse == cls.getSimpleName().hashCode();
    }

    public boolean whatEqual(Class request){
        if(request == null){
            return false;
        }
        Log.d("fang", "who =" + what + " request ="+request.getSimpleName());
        return what == request.getSimpleName().hashCode();
    }

    public boolean whatEqual(int what){
        return this.what == what;
    }

    public boolean requestOK(){
        if(BQNET_STATUS.OK.equals(netStatus)){
            return true;
        }
        return false;
    }

    public <T> T getNetResult(){
        return (T)obj;
    }

}
