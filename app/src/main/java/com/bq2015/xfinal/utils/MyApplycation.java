package com.bq2015.xfinal.utils;

import android.app.Application;

import com.bq2015.oknet.OkHttpUtils;
import com.bq2015.xfinal.request.Net;

/**
 *
 * Created by bq2015 on 2016/6/30.
 */
public class MyApplycation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttp();

    }

    private void initOkhttp() {
        OkHttpUtils.init(this);
        Net.NetInstance.setHttpHead();
    }
}
