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

import com.bq2015.bqhttp.event.BQNetEvent;

import org.greenrobot.eventbus.EventBus;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/3/23
 ************************************************************/
public enum BQEventBusManager {
    instance;

    private BQEventBusManager(){
        eventBus = EventBus.getDefault();
    }

    EventBus eventBus;
    public void post(BQEvent ydMessage){
        eventBus.post(ydMessage);
    }
    public void postNet(BQNetEvent BQNetEvent){
        eventBus.post(BQNetEvent);
    }

    public void postSocket(BQSocketEventMsg ydNetEvent){
        eventBus.post(ydNetEvent);
    }

    public void cancleEvent(Object obj){
        eventBus.cancelEventDelivery(obj);
    }


    public void registEvent(Object object){
        eventBus.register(object);
    }

    public void unRegistEvent(Object object){
        eventBus.unregister(object);
    }

}
