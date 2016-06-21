/*
 *
 *
 *    Copyright 2016 YunDi
 *
 *    The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *    DO NOT DIVULGE
 *
 *
 */

package com.bq2015.bqlib.mvvm;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.bq2015.bqlib.SystemTool;


/**
 * @author bq2015 on 2016/5/24.
 */
public abstract class CommonApplication extends MultiDexApplication {
    private static CommonApplication sInstance;
    private boolean appDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        appDebug = getAppDebug();
        final String processName = SystemTool.getProcessName(this, android.os.Process.myPid());
        boolean defaultProcess = !TextUtils.isEmpty(processName) && processName.equals(this.getPackageName());

        if (defaultProcess) {
            defaultProcessInit();
        }
    }

    protected abstract boolean getAppDebug();

    protected abstract void defaultProcessInit();


    public static CommonApplication getsInstance() {
        return sInstance;
    }

    public boolean isAppDebug() {
        return appDebug;
    }
}
