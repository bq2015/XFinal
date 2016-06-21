package com.bq2015.bqlib.event;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/6/6
 ************************************************************/
public interface OnRegistBQSocketEventListener {

    /**
     * socket相关的接口
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    void onYDSocketMsgReceived(BQSocketEventMsg event);
    @Subscribe(threadMode = ThreadMode.ASYNC)
    void onYDSocketMsgReceivedAsync(BQSocketEventMsg event);

}
