/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqlib;


import android.content.Context;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

/************************************************************
 * ,* Author:  bq2015
 * ,* Description:  复写的application
 * ,* Date: 2016/2/23
 ************************************************************/
public class AppController extends android.support.multidex.MultiDexApplication {

    public static final String TAG = AppController.class
            .getSimpleName();


    private static AppController mInstance;
    public PatchManager mPatchManager;

    protected String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        android.app.ActivityManager mActivityManager = (android.app.ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (android.app.ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        String processName = getCurProcessName(this);
        String appInfo = getPackageName();

        //当包名和进程名一致则表示该进程是主进程
        if (processName != null && processName.equals(appInfo)) {
            //RequestManager.createInstance(this);
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(this);
            //andFix
            try {
                mPatchManager = new PatchManager(this);
                mPatchManager.init(MiscTool.getVersionName(this));
                mPatchManager.loadPatch();
            } catch (Exception e) {
                Log.d("hotfix", e.getMessage());
            }

        }

    }

    @Override
    public void onLowMemory() {
        Log.e("runtesttag", "\n===================================");
        Log.e("runtesttag", "========!!!!!!!!!!!!!!!!!!=========");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "====系统内存过低,全局参数即将被回收========");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "===================================");
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        Log.e("runtesttag", "\n===================================");
        Log.e("runtesttag", "========!!!!!!!!!!!!!!!!!!=========");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "====系统内存过低,application被回收======");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "===================================");
        super.onTerminate();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}
