/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqhttp.net;

import com.bq2015.oknet.cache.CacheMode;
import com.bq2015.oknet.model.HttpParams;

import java.lang.reflect.Type;

public class NetRequestData {

    public enum HttpRequestType {
        GET, POST,POSTJSON
    }

    public enum HttpRequestContent {
        DEFAULT, FILE
    }

    static final int REQUEST_FILE = 1;
    static final int REQUEST_DEFAULT = 3;

    public String url;
    public HttpRequestType type;
    public HttpRequestContent requestContent;
    public Type[] types;

    public String methodName;
    public HttpParams params;
    public String jsonParam;
    public CacheMode cacheMode;
}
