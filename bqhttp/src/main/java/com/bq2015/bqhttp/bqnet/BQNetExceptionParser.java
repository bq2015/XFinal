package com.bq2015.bqhttp.bqnet;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.bq2015.oknet.exception.UnknowExceptionParser;
import com.bq2015.oknet.utils.Cons;

/************************************************************
 * Author:  bq2015
 * Description: 自定义网络异常解析类
 * Date: 2016/6/1
 ************************************************************/
public class BQNetExceptionParser extends UnknowExceptionParser {

    private static final String className = BQNetExceptionParser.class.getSimpleName();

    /**
     * 自定义异常解析
     * @param e
     * @param handler
     * @return
     */
    @Override
    protected boolean handler(@NonNull Throwable e, @NonNull IHandler handler) {
        //捕捉到自定义异常
        Log.v("YDNet", className + "errorMsg：" + e.toString());
       // int    code = ((BQNetUnkownException) e).getErrorCode();
       // String msg  = ((BQNetUnkownException) e).getErrorMsg();

        BQNetInfo netInfo = new BQNetInfo();
        //netInfo.setCode(code);
       // netInfo.setMsg(msg);

        if (JSONException.class.isAssignableFrom(e.getClass())) {
            handler.onHandler(Cons.Error.UnKnow, JSON.toJSONString(netInfo));
        } else if (BQNetUnkownException.class.isAssignableFrom(e.getClass())) {
            handler.onHandler(Cons.Error.UnKnow, JSON.toJSONString(netInfo));
        } else {
            //String errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
            handler.onHandler(Cons.Error.UnKnow, JSON.toJSONString(netInfo));
        }
        return true;
    }

}
