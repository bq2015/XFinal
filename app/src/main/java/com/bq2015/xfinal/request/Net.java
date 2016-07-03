package com.bq2015.xfinal.request;

import com.bq2015.oknet.OkHttpUtils;
import com.bq2015.oknet.cache.CacheMode;
import com.bq2015.oknet.model.HttpHeaders;
import com.bq2015.oknet.utils.ApiHelper;
import com.bq2015.xfinal.Constant;

/**
 *
 * Created by bq2015 on 2016/6/30.
 */
public class Net {
    public static class NetInstance {
       //基础域名
        public static String URL = Constant.NET.HOST;
        //设置基础域名
        public static OkHttpUtils okHttpUtils = OkHttpUtils.getInstance().baseUrl(URL);
        //获取请求Api
        public static NetApi getApi() {
            return ApiHelper.get(NetApi.class, okHttpUtils.getContext());
        }

        private  static  HttpHeaders httpHeaders = new HttpHeaders();
        /**
         * 设置请求头
         */
        public static void setHttpHead() {
          //  httpHeaders.put("key","value");
        }


    }

    public static NetApi get() {
        NetInstance.okHttpUtils.setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST);
        return NetInstance.getApi();
    }
}
