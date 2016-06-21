package com.bq2015.bqhttp.net;

import com.bq2015.bqhttp.event.YDNetEvent;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/6/3
 ************************************************************/
public interface OnYDNetEventListener {

    public abstract void netRequestSuccess(YDNetEvent event);
    public boolean netRequestFail(YDNetEvent event);

}
