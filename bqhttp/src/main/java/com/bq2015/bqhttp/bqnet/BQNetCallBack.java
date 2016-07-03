package com.bq2015.bqhttp.bqnet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bq2015.bqhttp.event.BQNET_STATUS;
import com.bq2015.bqhttp.event.BQNetEvent;
import com.bq2015.bqhttp.event.BQNetEventBuilder;
import com.bq2015.bqhttp.net.NetRequest;
import com.bq2015.bqhttp.net.OnBQNetEventListener;
import com.bq2015.oknet.callback.StringCallback;
import com.bq2015.oknet.modeinterface.INetView;
import com.bq2015.oknet.utils.Cons;

import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/************************************************************
 * Author:  bq2015
 * Description: 自定义网络回调
 * Date: 2016/5/31
 ************************************************************/
public class BQNetCallBack extends StringCallback {

    private Type[] types;
    private NetRequest netRequest;
    private BQNetInfo netInfo;

    public void setTypes(Type[] types) {
        this.types = types;
    }

    private Context context;
    private INetView iNetView;
    private OnBQNetEventListener onYdNetEventListener;

    public BQNetCallBack(Context context, INetView iNetView, OnBQNetEventListener onYdNetEventListener) {
        this.context = context;
        this.iNetView = iNetView;
        this.onYdNetEventListener = onYdNetEventListener;

        //此方法添加自定义异常
        addExceptionParser(new BQNetExceptionParser());
    }

    @Override
    public String parseNetworkResponse(Response response) throws Exception {

        String responseString = response.body().string();
        Log.d("fqq-->responseString=", responseString);
        if (!TextUtils.isEmpty(responseString)) {
            netInfo = new BQNetInfo();
            //原字符串
            netInfo.setOrignJson(responseString);

            if (null != netRequest) {
                new BQNetPrintLog(netRequest.data, netInfo).print();
            }

            if (BQNET_STATUS.OK.getValue() == netInfo.getCode()) {
                return responseString;
            } else {
                //默认抛出
               // throw new BQNetUnkownException(netInfo.getCode(), netInfo.getMsg());
            }

        }
        return responseString;
    }

    @Override
    public void onResponse(boolean isFromCache, String responseString, Request request, @Nullable Response response) {
        if (iNetView != null) {
            iNetView.dissmissLoadingView();
        }
        Log.d("fqq->responseString=", responseString);
        Object result = null;
        if (!TextUtils.isEmpty(responseString)  && this.types != null && this.types.length > 0) {
            Log.d("fqq-->ClassName=", this.types[0].getClass().getSimpleName());
            result = JSON.parseObject(responseString, this.types[0]);
        }

        BQNetEvent event = new BQNetEventBuilder().who(netRequest)
                .code1(BQNET_STATUS.OK.getValue())
                .bqNetStatus(BQNET_STATUS.OK)
                .obj(result)
                .context(context)
                .repMsg("网络请求成功")
                .createBQNetEvent();
        if (null != onYdNetEventListener) {
            onYdNetEventListener.netRequestSuccess(event);
        }
    }

    @Override
    public void onSimpleError(Cons.Error error, String message) {

        if (iNetView != null) {
            iNetView.dissmissLoadingView();
        }

        if (!TextUtils.isEmpty(message) && (Cons.Error.UnKnow == error)) {
            BQNetInfo netInfo = JSON.parseObject(message, BQNetInfo.class);
            int errorCode = -1;
            String errorMsg = null;
            if (netInfo != null) {
                errorCode = netInfo.getCode();
                errorMsg = netInfo.getMsg();
            }

            BQNET_STATUS YDNETSTATUS = null;
            if (errorCode == BQNET_STATUS.JSONPARSE_FAIL.getValue()) {
                //未知错误
                YDNETSTATUS = BQNET_STATUS.JSONPARSE_FAIL;
                //errorMsg = "JSON解析错误：" + errorMsg;
            } else if (errorCode == BQNET_STATUS.YD_UNKOWN_ERROR.getValue()) {
                //未知错误
                YDNETSTATUS = BQNET_STATUS.YD_UNKOWN_ERROR;
                //errorMsg = "未知错误：" + errorMsg;
            } else if (errorCode == BQNET_STATUS.NO_CODE.getValue()) {
                //服务器返回信息中没有code字段
                YDNETSTATUS = BQNET_STATUS.NO_CODE;
                //errorMsg = "错误：服务器返回信息中没有code字段" + errorMsg;
            } else if (errorCode == BQNET_STATUS.NO_MORE_DATA.getValue()) {
                //没有更多数据
                YDNETSTATUS = BQNET_STATUS.NO_MORE_DATA;
                //errorMsg = "错误：没有更多数据" + errorMsg;
            } else if (errorCode == BQNET_STATUS.TOKEN_VERIFY_FAILED.getValue()) {
                //请重新登录
                YDNETSTATUS = BQNET_STATUS.TOKEN_VERIFY_FAILED;
                //errorMsg = "错误：没有更多数据" + errorMsg;
            } else if (errorCode == BQNET_STATUS.TOKEN_OVERDUE.getValue()) {
                //Token过期
                YDNETSTATUS = BQNET_STATUS.TOKEN_OVERDUE;
                //errorMsg = "错误：Token过期" + errorMsg;
            } else if (errorCode == BQNET_STATUS.ACCOUNT_LOGINED.getValue()) {
                //用户已经登录
                YDNETSTATUS = BQNET_STATUS.ACCOUNT_LOGINED;
                //errorMsg = "错误：用户已经登录" + errorMsg;
            } else if (errorCode == BQNET_STATUS.MISSING_PARAMETERS.getValue()) {
                //缺少参数
                YDNETSTATUS = BQNET_STATUS.MISSING_PARAMETERS;
                //errorMsg = "错误：缺少参数" + errorMsg;
            } else if (errorCode == BQNET_STATUS.SERVER_ERROR.getValue()) {
                //服务器错误
                YDNETSTATUS = BQNET_STATUS.SERVER_ERROR;
                //errorMsg = "错误：服务器错误" + errorMsg;
            }

            //网络异常时的回调处理
            BQNetEvent event = new BQNetEventBuilder()
                    .who(netRequest)
                    .code1(errorCode)
                    .context(context)
                    .bqNetStatus(YDNETSTATUS)
                    .repMsg(errorMsg)
                    .createBQNetEvent();

            if (null != onYdNetEventListener) {
                if (!onYdNetEventListener.netRequestFail(event)) {
                    if (context != null) {
                        //自定义异常处理
                        //Toast.makeText(context, "errorCode：" + errorCode + "------" +
                        //               "errorMsg：" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public boolean netRequestFail(BQNetEvent event) {
        return false;
    }

    /**
     * @param request
     */
    public void setRequest(NetRequest request) {
        this.netRequest = request;
    }
}