/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqlib.event;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/************************************************************
 * Author:  bq2015
 * Description:
 *          Eventbus消息机制：
 *              当有一条消息post后，会随机顺序执行以下四个监听方法；
 *              ！！！！！推荐使用 onYDEventMain 和 onYDEventAsync
 * Date: 2016/3/23
 ************************************************************/
public interface OnRegistBQEventListener {


    /**
     * 接收的事件规则：固定在主线程中间
     * @param event
     */
    void onYDEventMain(BQEvent event) ;
    /**
     * 接收的事件规则：
     * 1 消息在主线程post,该方法在新new的子线程中间执行
     * 2 消息在子线程post,该方法在p新new的子线程中执行
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    void onYDEventAsync(BQEvent event);

    /**
     * 接收的事件规则：
     * 1 消息在主线程post,该方法在新new的子线程中间执行
     * 2 消息在子线程post,该方法在post的子线程中执行
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    void onYDEventBackgound(BQEvent event) ;

    /**
     * 接收的事件规则：在post消息的线程的线程中执行
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    void onYDEventPosting(BQEvent event);

}
