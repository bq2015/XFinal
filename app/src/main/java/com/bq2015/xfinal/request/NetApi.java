package com.bq2015.xfinal.request;

import com.bq2015.bqhttp.net.NetRequest;
import com.bq2015.oknet.cache.CacheMode;
import com.bq2015.oknet.modeinterface.annotation.CACHE;
import com.bq2015.oknet.modeinterface.annotation.GET;
import com.bq2015.oknet.modeinterface.annotation.PARAMS;
import com.bq2015.xfinal.model.BaseNetList;
import com.bq2015.xfinal.model.DishCategory;
import com.bq2015.xfinal.model.StockInfo;

/**
 * Created by bq2015 on 2016/6/30
 */
public interface NetApi {
    /**
     * 第一步：定义请求接口（提前写好封装服务器返回数据用的Bean）
     */
    @CACHE(CacheMode.FIRST_CACHE_THEN_REQUEST) //先从缓存读取，再请求网络
    @GET("http://api.avatardata.cn/Stock/CN")
    NetRequest<StockInfo> getStockInfos(@PARAMS("key") String key  //应用APPKEY
            , @PARAMS("stockid") String stockid                    //	沪深股票ID，最多查询10支股票代码，
            , @PARAMS("list") int list                             //list=1,表示一次查询多支股票，
            , @PARAMS("dtype") String dtype                        //返回结果格式：可选JSON/XML，默认JSON
            , @PARAMS("format") boolean format);                   //当返回结果格式为JSON时，


    @GET("/pmt-jfpayment-1.3-SNAPSHOT/dishes/dishTypeList/")
    NetRequest<BaseNetList<DishCategory>> dishCategories(@PARAMS("apiKey") int apiKey);
                            // 是否对其进行格式化，为了节省流量默认为false，测试时您可以传入true来熟悉返回内容

}
