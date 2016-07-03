package com.bq2015.bqhttp.net;

import com.bq2015.bqhttp.event.BQNetEvent;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/6/3
 ************************************************************/
public interface OnBQNetEventListener {

    public abstract void netRequestSuccess(BQNetEvent event);
    public boolean netRequestFail(BQNetEvent event);

}
