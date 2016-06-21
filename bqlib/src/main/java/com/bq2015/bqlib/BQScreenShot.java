package com.bq2015.bqlib;

import android.app.Activity;
import android.graphics.Bitmap;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/5/13
 ************************************************************/
public class BQScreenShot {

    public static Bitmap captureScreen(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp=activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }


}
