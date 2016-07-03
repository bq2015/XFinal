package com.bq2015.bqhttp.bqnet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bq2015.bqhttp.bqcallback.JsonCallback;
import com.bq2015.bqhttp.event.BQNET_STATUS;
import com.bq2015.bqhttp.event.BQNetEvent;
import com.bq2015.bqhttp.event.BQNetEventBuilder;
import com.bq2015.oknet.utils.Cons;

import okhttp3.Request;
import okhttp3.Response;

/************************************************************
 * Author:  bq2015
 * Description: 自定义网络回调
 * Date: 2016/5/31
 ************************************************************/
public abstract class BQNetJsonCallBack<T> extends JsonCallback<T> {

    private static final String className = BQNetExceptionParser.class.getSimpleName();

    private Context context;

    public BQNetJsonCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(boolean isFromCache, T t, Request request, @Nullable Response response) {

        //网络请求成功
        BQNetEvent event = new BQNetEventBuilder().who(request)
                .code1(BQNET_STATUS.OK.getValue())
                .bqNetStatus(BQNET_STATUS.OK)
                .obj(t)
                .context(context)
                .repMsg("网络请求成功")
                .createBQNetEvent();

            this.netRequestSuccess(event);
    }

    @Override
    public void onSimpleError(Cons.Error error, String message) {

        Log.v("YDNet", className + "errorMessage：" + message);
        if (!TextUtils.isEmpty(message) && (Cons.Error.UnKnow == error)) {
            BQNetUnkownException ydNetUnkownException = JSON.parseObject(message, BQNetUnkownException.class);
            int    errorCode = ydNetUnkownException.getErrorCode();
            String errorMsg  = ydNetUnkownException.getErrorMsg();

            BQNET_STATUS YDNETSTATUS = null;

            if (errorCode == BQNET_STATUS.YD_UNKOWN_ERROR.getValue()){
                //未知错误
                YDNETSTATUS = BQNET_STATUS.YD_UNKOWN_ERROR;
                errorMsg = "未知错误：" + errorMsg;
            } else if (errorCode == BQNET_STATUS.NO_CODE.getValue()) {
                //服务器返回信息中没有code字段
                YDNETSTATUS = BQNET_STATUS.NO_CODE;
                errorMsg = "错误：服务器返回信息中没有code字段" + errorMsg;
            } else if (errorCode == BQNET_STATUS.NO_MORE_DATA.getValue()) {
                //没有更多数据
                YDNETSTATUS = BQNET_STATUS.NO_MORE_DATA;
                errorMsg = "错误：没有更多数据" + errorMsg;
            } else if (errorCode == BQNET_STATUS.TOKEN_VERIFY_FAILED.getValue()) {
                //请重新登录
                YDNETSTATUS = BQNET_STATUS.TOKEN_VERIFY_FAILED;
                errorMsg = "错误：没有更多数据" + errorMsg;
            } else if (errorCode == BQNET_STATUS.TOKEN_OVERDUE.getValue()) {
                //Token过期
                YDNETSTATUS = BQNET_STATUS.TOKEN_OVERDUE;
                errorMsg = "错误：Token过期" + errorMsg;
            } else if (errorCode == BQNET_STATUS.ACCOUNT_LOGINED.getValue()) {
                //用户已经登录
                YDNETSTATUS = BQNET_STATUS.ACCOUNT_LOGINED;
                errorMsg = "错误：用户已经登录" + errorMsg;
            } else if (errorCode == BQNET_STATUS.MISSING_PARAMETERS.getValue()) {
                //缺少参数
                YDNETSTATUS = BQNET_STATUS.MISSING_PARAMETERS;
                errorMsg = "错误：缺少参数" + errorMsg;
            } else if (errorCode == BQNET_STATUS.SERVER_ERROR.getValue()) {
                //服务器错误
                errorMsg = "错误：服务器错误" + errorMsg;
                YDNETSTATUS = BQNET_STATUS.SERVER_ERROR;
            }

            //网络异常时的回调处理
            if (null != YDNETSTATUS) {
                //网络请求异常
                Log.v("YDNet", className + "errorCode：" + errorCode + "------" +  "errorMsg：" + errorMsg) ;
                BQNetEvent event = new BQNetEventBuilder()
                        .code1(YDNETSTATUS.getValue())
                        .context(context)
                        .bqNetStatus(YDNETSTATUS)
                        .repMsg(errorMsg)
                        .createBQNetEvent();

                if (!this.netRequestFail(event)){
                    //自定义异常处理
                    Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public abstract void netRequestSuccess(BQNetEvent event);

    public boolean netRequestFail(BQNetEvent event) {
        Log.v("YDNet", className + "网络请求失败：" + event.toString());
        return false;
    }

}
