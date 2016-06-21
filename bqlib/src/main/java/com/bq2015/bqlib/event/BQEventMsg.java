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

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/3/23
 ************************************************************/
public class BQEventMsg {

    public int what;
    public int arg1;
    public int arg2;
    public Object obj;

    public BQEventMsg() {
    }

    public BQEventMsg(int what, int arg1, int arg2, Object obj) {
        this.what = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj = obj;
    }

    public boolean whatEquals(int otherWhat){
        return otherWhat == what;
    }

    public <T> T getObj(){
        return (T)obj;
    }

}
