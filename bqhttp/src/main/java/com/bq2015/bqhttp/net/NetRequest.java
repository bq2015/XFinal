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

import android.content.Context;

import com.bq2015.bqhttp.bqnet.BQNetCallBack;
import com.bq2015.oknet.OkHttpUtils;
import com.bq2015.oknet.callback.AbsCallback;
import com.bq2015.oknet.modeinterface.INetView;
import com.bq2015.oknet.request.BaseRequest;
import com.bq2015.oknet.request.GetRequest;
import com.bq2015.oknet.request.PostRequest;

public class NetRequest<T> {

    public Object what;
    public NetRequestData data;
    public Context context;
    private INetView iView;

    private PostRequest postRequest;
    private GetRequest  getRequest;

    /**
     * 显示网络请求进度条
     * @param iView
     * @return
     */
    public NetRequest<T> showProgress(INetView iView){
        return showProgress(iView, null);
    }

    /**
     * 显示自定义网络请求提醒文字
     * @param iView
     * @return
     */
    public NetRequest<T> showProgress(INetView iView, int textRes){
        if (null != context) {
            String textProgress = context.getString(textRes);
            return showProgress(iView, textProgress);
        } else {
            return showProgress(iView, null);
        }
    }

    /**
     * 显示自定义网络请求提醒文字
     * @param iView
     * @return
     */
    public NetRequest<T> showProgress(INetView iView, String textProgress){
        this.iView = iView;
        //show the net request progress
        if (null != iView) {
            iView.showLoadingView(textProgress);
        }
        return this;
    }

    public NetRequest<T> execute(OnBQNetEventListener onYdNetEventListener){

      if (data != null) {
          if (onYdNetEventListener == null) {
              throw new IllegalArgumentException("net listener not be null");
          }

          BQNetCallBack callback = new BQNetCallBack(context, iView, onYdNetEventListener);
          callback.setTypes(data.types);

          what = this;

          switch (data.type) {
              case GET:
                  this.getRequest = OkHttpUtils.get(data.url);
                  execInner(getRequest, callback);
                  break;
              case POST:
                  this.postRequest = OkHttpUtils.post(data.url);
                  execInner(postRequest, callback);
                  break;
              case POSTJSON:
                  this.postRequest = OkHttpUtils.post(data.url);
                  postRequest.postJson(data.jsonParam);
                  execInner(postRequest, callback);
                  break;
          }
      }
        return this;
    }

    /**
     * 回调执行，暂不暴露
     * @param callback
     * @return
     */
    private NetRequest<T> execute(AbsCallback<T> callback) {

        if (callback == null) {
            throw new IllegalArgumentException("callback not be null");
        }

        what = this;

        switch (data.type) {
            case GET:
                execInner(OkHttpUtils.get(data.url), callback);
                break;
            case POST:
                execInner(OkHttpUtils.post(data.url), callback);
                break;
            case POSTJSON:
                PostRequest r = OkHttpUtils.post(data.url);
                r.postJson(data.jsonParam);
                execInner(r, callback);
                break;
        }
        return this;
    }

    private void execInner(BaseRequest req, AbsCallback callback) {
        req.cacheMode(data.cacheMode);
        req.params(data.params);
        req.tag(this);
        if (callback instanceof BQNetCallBack) {
            if (req instanceof PostRequest) {
                this.postRequest = (PostRequest) req;
            } else if (req instanceof GetRequest) {
                this.getRequest = (GetRequest) req;
            }
            ((BQNetCallBack)callback).setRequest(this);
        }
        req.execute(callback);
    }

    public void cancel() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
