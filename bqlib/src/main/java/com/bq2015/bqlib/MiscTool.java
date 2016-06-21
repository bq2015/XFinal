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

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Locale;
import java.util.UUID;

/************************************************************
 * ,* Author:  bq2015
 * ,* Description:  一些小的工具函数，单个函数可以搞定的放这里
 * ,* Date: 2016/2/22 17:26
 ************************************************************/
public class MiscTool {

    // 加密
    public static String normalEncode(String encode, String type) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance(type).digest(encode.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    //	md5加密
    public static String md5(String encode) {
        return normalEncode(encode, "MD5");
    }

    // SHA加密
    public static String sha(String encode) {
        return normalEncode(encode, "SHA");
    }

    // 生成sig
    public static String generateSig(String uid, String phone, String imei, String time) {
        return generateSigJni(uid, phone, imei, time);
    }

    //	包装的系统的json接口，做了容错，
    public static <T> T getJson(JSONObject obj, String key, T value) {
        try {
            if (obj.isNull(key)) {
                return value;
            }
            return (T) obj.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    //    对象转byte数组
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // result to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    //    int转byte[]，大端
    public static byte[] IntToByte(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) ((i) & 0xFF);

        return result;
    }

    //    获取meta值
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String result = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        result = applicationInfo.metaData.get(key).toString();
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 获取版本号
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    // 获取版本名
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /**
     * 获取安卓系统版本
     *
     * @return
     */
    public static String getOsversion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getBrand() {
        return Build.VERSION.RELEASE;
    }


    /**
     * 获取imei
     *
     * @return
     */
    public static String getIMEI(Context context) {
        try {
            String imei = ((TelephonyManager) context.getSystemService(
                    Context.TELEPHONY_SERVICE)).getDeviceId();
            if (imei == null) {
                imei = md5(getSn());
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMyUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }

    /**
     * @return 手机型号
     */
    public static String getBrandId() {
        return Build.MODEL;
    }


    /**
     * @return Mac地址
     */
    public static String getMac(Context ctx) {

        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 获取SIM卡号
     */
    public static String getSim(Context context) {
        String strResult = "";
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            strResult = telephonyManager.getSimSerialNumber();
        }
        return strResult;
    }

    /**
     * 获取语言Str
     *
     * @param context
     * @return
     */
    public static String getLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage();
    }

    public static String getSn() {
        String ret;
        try {
            Method systemProperties_get = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            if ((ret = (String) systemProperties_get.invoke(null, "ro.serialno")) != null)
                return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return "";
    }


    // gps是否开启
    public static Boolean isGpsEnable() {
        LocationManager locationManager = (LocationManager) AppController.getInstance().
                getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    // 进入设置gps的界面，没有的话去总的设置界面
    public static void startGpsSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            AppController.getInstance().startActivity(intent);


        } catch (ActivityNotFoundException ex) {

            // The Android SDK doc says that the location settings activity
            // may not be found. In that case show the general settings.

            // General settings activity
            intent.setAction(Settings.ACTION_SETTINGS);
            try {
                AppController.getInstance().startActivity(intent);
            } catch (Exception e) {
            }
        }
    }

    // http加密
    public static final String httpEncode(String data) {
        final String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCp5s/j1imEkC2KM4BawWPvivTF" +
                "hiOQ6CpmZ6FbLuIS2bVtWXBQsk+LIC1ucXmTsE9B7z+bnVi1mlJdkCRpwbXka3AS" +
                "ESRUlHhiLxxgQ8sBCaprrW87l9ZU8hUr7GaTshzFwS3UfKK1xCs3d0+3DU+VjYZ7" +
                "lw36Df5lyerdly8xtQIDAQAB";
        PublicKey publicKey = null;
        try {
            publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64Utils.encode(RSAUtils.encryptData(data.getBytes(), publicKey));
    }

    /**
     * 获取国家
     *
     * @param context
     * @return
     */
    public static final String getCountry(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getConfiguration().locale.getCountry();
    }

    /**
     * 获取地图类型
     *
     * @return
     */
    public static final String getMaptype() {
       /* if (Strategy.MAP_TYPE == Strategy.BAIDU) {
            return "baidu";
        } else if (Strategy.MAP_TYPE == Strategy.GAODE) {
            return "gaode";
        }*/
        return "";
    }

    /**
     * 获取网络运营商
     *
     * @param context
     * @return
     */
    public static int getMobileType(Context context) {
        int type = 0;
        TelephonyManager iPhoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String iNumeric = iPhoneManager.getSimOperator();
        if (iNumeric.length() > 0) {
            if (iNumeric.equals("46000") || iNumeric.equals("46002")) {
                return 1;
            } else if (iNumeric.equals("46001")) {
                return 2;
            } else if (iNumeric.equals("46003")) {
                return 3;
            }
        }
        return 0;
    }


    public static native String generateSigJni(String uid, String phone, String imei, String time);

    static {
        System.loadLibrary("NdkTools");
    }

    public static void installApk(Context c, File file) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
        c.startActivity(i);
    }

    /**
     * 将一个浮点型保留一定位数
     *
     * @param source 源
     * @param number 保留位数
     * @return
     */
    public static float floatTransfer(Float source, int number) {
        float result = 0f;
        Integer numberTmp = (int) Math.pow(10, number);
        result = (float) (Math.round(source * numberTmp)) / numberTmp;
        return result;
    }
}
