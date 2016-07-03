package com.bq2015.oknet.callback;

import okhttp3.Response;

/**
 * 返回字符串类型的数据
 */
public abstract class StringCallback extends AbsCallback<String> {

    public String parseNetworkResponse(Response response) throws Exception {
        return response.body().string();
    }
}
