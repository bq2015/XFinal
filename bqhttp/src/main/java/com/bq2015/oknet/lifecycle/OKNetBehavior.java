package com.bq2015.oknet.lifecycle;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author bq2015 2016/6/15.
 */
@IntDef(flag = false, value = {OKNetBehavior.STOP, OKNetBehavior.DESTROY})
@Retention(RetentionPolicy.SOURCE)
public @interface OKNetBehavior {
    int STOP = 0;
    int DESTROY = 1;
}
