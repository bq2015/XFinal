package com.bq2015.bqhttp.bqnet;

import android.text.TextUtils;
import android.util.Log;

import com.bq2015.bqhttp.net.NetRequestData;
import com.bq2015.oknet.OkHttpUtils;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/4/21
 ************************************************************/
public class BQNetPrintLog {

    NetRequestData data;
    BQNetInfo netinfo;

    public BQNetPrintLog(NetRequestData netRequestData, BQNetInfo netInfo){
        this.data = netRequestData;
        this.netinfo = netInfo;
    }

    public void print(){

        String string = "url:-->" + OkHttpUtils.getInstance().getBaseUrl() + data.url+" \n ";

        try {
            string += "请求头:-->" + OkHttpUtils.getInstance().getCommonHeaders() == null ? "" : OkHttpUtils.getInstance().getCommonHeaders() + " \n ";
        }catch (Exception e){
            e.printStackTrace();
        }

        string += "请求方式：-->" + data.type.toString()+ " \n ";
        if (data.params != null) {
            string+="请求参数:-->" + data.params.toString()+" \n ";
        } else {
            string+="请求参数:-->" +"空"+" \n ";
        }

        string += ("返回结果：" + (TextUtils.isEmpty(netinfo.getOrignJson()) ?
                (TextUtils.isEmpty(netinfo.getMsg()) ?
                 "空":netinfo.getMsg()) :
                 netinfo.getOrignJson().replace(",", ", \n ")));

        //String msg = netinfo.getMsg() == null ? "空" : netinfo.getMsg();
        //Log.d("ydhttp", "状态码:-->" + netCode);
        //Log.d("ydhttp", "Msg:-->" + msg);
        Log.d("YDNet", string);

    }


}
