package com.bq2015.xfinal.request;

import com.bq2015.bqhttp.net.NetRequest;
import com.bq2015.oknet.modeinterface.annotation.GET;
import com.bq2015.oknet.modeinterface.annotation.PARAMS;
import com.bq2015.xfinal.model.BaseNetList;
import com.bq2015.xfinal.model.DishCategory;
import com.bq2015.xfinal.model.StockInfo;

/**
 * Created by bq2015 on 2016/6/30
 */
public interface NetApi {


    @GET("/pmt-jfpayment-1.3-SNAPSHOT/dishes/dishTypeList/")
    NetRequest<BaseNetList<DishCategory>> dishCategories(@PARAMS("apiKey") int apiKey);

    @GET("http://api.avatardata.cn/Stock/CN")
    NetRequest<StockInfo> getStockInfos(@PARAMS("key") String key//应用APPKEY
            , @PARAMS("stockid") String stockid //	沪深股票ID，最多查询10支股票代码，
                                        // 多于10支则查前10支，股票代码请自行查阅股市大盘，
                                        // 如果list=1，则可以查询多支股票，用逗号（,）隔开
            , @PARAMS("list") int list //list=1,表示一次查询多支股票，list=其它值，则一次只查一支股票
            , @PARAMS("dtype") String dtype//返回结果格式：可选JSON/XML，默认为JSON
            , @PARAMS("format") boolean format);//当返回结果格式为JSON时，
                            // 是否对其进行格式化，为了节省流量默认为false，测试时您可以传入true来熟悉返回内容

}
