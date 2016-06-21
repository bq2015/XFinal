package com.bq2015.oknet.bqnet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.bq2015.bqhttp.event.YDNET_STATUS;
import com.bq2015.bqhttp.event.YDNetEvent;
import com.bq2015.bqhttp.event.YDNetEventBuilder;
import com.bq2015.bqhttp.net.NetRequest;
import com.bq2015.bqhttp.net.OnYDNetEventListener;
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
    private OnYDNetEventListener onYdNetEventListener;

    public BQNetCallBack(Context context, INetView iNetView, OnYDNetEventListener onYdNetEventListener) {
        this.context = context;
        this.iNetView = iNetView;
        this.onYdNetEventListener = onYdNetEventListener;

        //此方法添加自定义异常
        addExceptionParser(new BQNetExceptionParser());
    }

    @Override
    public String parseNetworkResponse(Response response) throws Exception {

        String responseString = response.body().string();
        if (!TextUtils.isEmpty(responseString)) {
            netInfo = com.alibaba.fastjson.JSONObject.parseObject(responseString, BQNetInfo.class);
            if (netInfo != null) {
                //原字符串
                netInfo.setOrignJson(responseString);

                if(null != netRequest){
                    new BQNetPrintLog(netRequest.data, netInfo).print();
                }

                if (YDNET_STATUS.OK.getValue() == netInfo.getCode()) {
                    return responseString;
                } else {
                    //默认抛出
                    throw new BQNetUnkownException(netInfo.getCode(), netInfo.getMsg());
                }
            }
        }
        return responseString;
    }

    @Override
    public void onResponse(boolean isFromCache, String responseString, Request request, @Nullable Response response) {
        if (iNetView != null) {
            iNetView.dissmissLoadingView();
        }

        Object result = null;
        //返回字符串不为空
        int    code = -1;
        String data = null;
        if (netInfo != null) {
            code = netInfo.getCode();
            data = netInfo.getData();
        } else if (!TextUtils.isEmpty(responseString)){
            netInfo = com.alibaba.fastjson.JSONObject.parseObject(responseString, BQNetInfo.class);
            if (null != netInfo) {
                code = netInfo.getCode();
                data = netInfo.getData();
            }
        }

        if (code == YDNET_STATUS.OK.getValue()) {

            if (!TextUtils.isEmpty(data)  && this.types != null && this.types.length > 0) {
                result = JSON.parseObject(data, this.types[0]);
            }

            YDNetEvent event = null;
            //网络请求成功
            event = new YDNetEventBuilder().what(netRequest)
                    .arg1(YDNET_STATUS.OK.getValue())
                    .ydNetStatus(YDNET_STATUS.OK)
                    .obj(result)
                    .context(context)
                    .repMsg("网络请求成功")
                    .createYDNetEvent();

            if (null != onYdNetEventListener) {
                onYdNetEventListener.netRequestSuccess(event);
            }
        }
    }

    @Override
    public void onSimpleError(Cons.Error error, String message) {

        if (iNetView != null) {
            iNetView.dissmissLoadingView();
        }

        if (!TextUtils.isEmpty(message) && (Cons.Error.UnKnow == error)) {
            BQNetInfo netInfo = JSON.parseObject(message, BQNetInfo.class);
            int    errorCode = -1;
            String errorMsg  = null;
            if (netInfo != null) {
                errorCode = netInfo.getCode();
                errorMsg  = netInfo.getMsg();
            }

            YDNET_STATUS YDNETSTATUS = null;
            if (errorCode == YDNET_STATUS.JSONPARSE_FAIL.getValue()) {
                //未知错误
                YDNETSTATUS = YDNET_STATUS.JSONPARSE_FAIL;
                //errorMsg = "JSON解析错误：" + errorMsg;
            } else if (errorCode == YDNET_STATUS.YD_UNKOWN_ERROR.getValue()) {
                //未知错误
                YDNETSTATUS = YDNET_STATUS.YD_UNKOWN_ERROR;
                //errorMsg = "未知错误：" + errorMsg;
            } else if (errorCode == YDNET_STATUS.NO_CODE.getValue()) {
                //服务器返回信息中没有code字段
                YDNETSTATUS = YDNET_STATUS.NO_CODE;
                //errorMsg = "错误：服务器返回信息中没有code字段" + errorMsg;
            } else if (errorCode == YDNET_STATUS.NO_MORE_DATA.getValue()) {
                //没有更多数据
                YDNETSTATUS = YDNET_STATUS.NO_MORE_DATA;
                //errorMsg = "错误：没有更多数据" + errorMsg;
            } else if (errorCode == YDNET_STATUS.TOKEN_VERIFY_FAILED.getValue()) {
                //请重新登录
                YDNETSTATUS = YDNET_STATUS.TOKEN_VERIFY_FAILED;
                //errorMsg = "错误：没有更多数据" + errorMsg;
            } else if (errorCode == YDNET_STATUS.TOKEN_OVERDUE.getValue()) {
                //Token过期
                YDNETSTATUS = YDNET_STATUS.TOKEN_OVERDUE;
                //errorMsg = "错误：Token过期" + errorMsg;
            } else if (errorCode == YDNET_STATUS.ACCOUNT_LOGINED.getValue()) {
                //用户已经登录
                YDNETSTATUS = YDNET_STATUS.ACCOUNT_LOGINED;
                //errorMsg = "错误：用户已经登录" + errorMsg;
            } else if (errorCode == YDNET_STATUS.MISSING_PARAMETERS.getValue()) {
                //缺少参数
                YDNETSTATUS = YDNET_STATUS.MISSING_PARAMETERS;
                //errorMsg = "错误：缺少参数" + errorMsg;
            } else if (errorCode == YDNET_STATUS.SERVER_ERROR.getValue()) {
                //服务器错误
                YDNETSTATUS = YDNET_STATUS.SERVER_ERROR;
                //errorMsg = "错误：服务器错误" + errorMsg;
            }

            //网络异常时的回调处理
            YDNetEvent event = new YDNetEventBuilder()
                        .what(netRequest)
                        .arg1(errorCode)
                        .context(context)
                        .ydNetStatus(YDNETSTATUS)
                        .repMsg(errorMsg)
                        .createYDNetEvent();

            if (null != onYdNetEventListener) {
                if(!onYdNetEventListener.netRequestFail(event)){
                    if (context != null) {
                        //自定义异常处理
                        //Toast.makeText(context, "errorCode：" + errorCode + "------" +
                        //               "errorMsg：" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public boolean netRequestFail(YDNetEvent event) {
        return false;
    }

    /**
     *
     * @param request
     */
    public void setRequest(NetRequest request) {
        this.netRequest = request;
    }
}