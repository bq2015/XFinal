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

public class BQEventBuilder {
    private int what;
    private int arg1;
    private int arg2;
    private Object obj;

    public BQEventBuilder what(int what) {
        this.what = what;
        return this;
    }

    public BQEventBuilder arg1(int arg1) {
        this.arg1 = arg1;
        return this;
    }

    public BQEventBuilder arg2(int arg2) {
        this.arg2 = arg2;
        return this;
    }

    public BQEventBuilder obj(Object obj) {
        this.obj = obj;
        return this;
    }

    public BQEvent create() {
        return new BQEvent(what, arg1, arg2, obj);
    }

}