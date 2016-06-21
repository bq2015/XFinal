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

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


/************************************************************
 * ,* Author:  bq2015
 * ,* Description:  网络工具类
 * ,* Date: 2016/3/2
 ************************************************************/
public class NetWorkUtils {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_2G = 2;
    public static final int NETWORN_3G = 3;
    public static final int NETWORN_4G = 4;

    private static String TAG = "NetWorkHelper";


    public static int GetNetworkType(Activity context)
    {
        int strNetworkType = NETWORN_NONE;

        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
        {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                strNetworkType = NETWORN_WIFI;
            }
            else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            {
                String _strSubTypeName = networkInfo.getSubtypeName();

                Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);

                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = NETWORN_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = NETWORN_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = NETWORN_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = NETWORN_3G;
                        } else {
                            strNetworkType = NETWORN_NONE;
                        }
                        break;
                }

            }
        }
        return strNetworkType;
    }

    /**
     * 判断是否有网络
     */
    public static boolean isNetworkAvailable() {
//        ConnectivityManager connectivity = (ConnectivityManager) AppController.getInstance()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (connectivity == null) {
//            Log.w(TAG, "couldn't get connectivity manager");
//        } else {
//            NetworkInfo[] info = connectivity.getAllNetworkInfo();
//            if (info != null) {
//                for (int i = 0; i < info.length; i++) {
//                    if (info[i].isAvailable()) {
//                        Log.d(TAG, "network is available");
//                        return true;
//                    }
//                }
//            }
//        }
//        Log.d(TAG, "network is not available");
//        return false;

        return (isMobileDataEnable() || isWifiDataEnable());
    }

    /**
     * 进入网络设置
     */
    public static void StartNetWorkSetting(Context context) {
        String sdkVersion = android.os.Build.VERSION.SDK;
        Intent intent = null;
        if (Integer.valueOf(sdkVersion) > 10) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings");
            intent.setComponent(comp);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }

    /**
     * 检查网络状态
     *
     * @return
     */
    public static boolean checkNetState() {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) AppController.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }

    /**
     * 判断网络是否为漫游
     */
    public static boolean isNetworkRoaming() {
        ConnectivityManager connectivity = (ConnectivityManager) AppController.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            android.util.Log.w(TAG, "couldn't get connectivity manager");
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null
                    && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager tm = (TelephonyManager) AppController.getInstance()
                        .getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null && tm.isNetworkRoaming()) {
                    android.util.Log.d(TAG, "network is roaming");
                    return true;
                } else {
                    Log.d(TAG, "network is not roaming");
                }
            } else {
                Log.d(TAG, "not using mobile network");
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) AppController.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;

        isMobileDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        return isMobileDataEnable;
    }


    /**
     * 判断wifi 是否可用
     *
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) AppController.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }


//    public static int getNetworkState() {
//        ConnectivityManager connManager = (ConnectivityManager) AppController.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        //Wifi
//        State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//        if (state == State.CONNECTED || state == State.CONNECTING) {
//            return NETWORN_WIFI;
//        }
//
//        //Mobile
//        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//        if (state == State.CONNECTED || state == State.CONNECTING) {
//            return NETWORN_MOBILE;
//        }
//        return NETWORN_NONE;
//    }


    private static WifiInfo getWifiInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo;
    }

    public static String getSSID() {
        String ssid = "";
        try {
            WifiInfo wifiInfo = NetWorkUtils.getWifiInfo(AppController.getInstance());
            ssid = wifiInfo.getSSID();
        } catch (Exception e) {
            Log.e(TAG, "ssid error");
        }
        return ssid;
    }

    public static String getMac() {
        String mac = "";
        try {
            WifiManager wifi = (WifiManager) AppController.getInstance().getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
            mac = info.getMacAddress();
        } catch (Exception e) {
            Log.e(TAG, "get mac address error");
        }
        return mac;
    }

    public static String getIpAddr() {
        String ip = "";
        try {
            WifiManager wifi = (WifiManager) AppController.getInstance().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            int ipnum = info.getIpAddress();
            ip = (ipnum & 0xFF) + "." +
                    ((ipnum >> 8) & 0xFF) + "." +
                    ((ipnum >> 16) & 0xFF) + "." +
                    ((ipnum >> 24) & 0xFF);
        } catch (Exception e) {
            Log.e(TAG, "get ip address error");
        }
        return ip;
    }
}

