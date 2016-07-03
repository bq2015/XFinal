package com.bq2015.oknet.utils;

import android.content.Context;

import java.lang.reflect.Proxy;

/**
 * @author bq2015 on 2016/5/25.
 */
@SuppressWarnings("all")
public class ApiHelper {
    public static <T> T get(Class<T> c, Context context) {
        return (T) Proxy.newProxyInstance(
                c.getClassLoader(),
                new Class[]{c},
                new NetUtil(context));
    }
}
