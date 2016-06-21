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

import com.bq2015.bqlib.widget.DrawerToast;


// 包装的DrawerToast
public class ToastUtils {

	
	//统一一个Toast实例
//	private static Toast mToast;
	private static DrawerToast mToast;
	
	/**
	 * 显示自定义的Toast消息
	 * @param textResId  资源id
	 */
	public static void showCustomMessage(int textResId){
		Context context = AppController.getInstance().getApplicationContext();
		String msg=context.getResources().getString(textResId);
		mToast= DrawerToast.getInstance(context);
		mToast.setDefaultBackgroundResource(R.drawable.common_toast_bg);
		mToast.show(msg);
	}
	
	/**
	 * 显示自定义的Toast信息  
	 * @param  message  自定义的消息
	 */
	public static void showCustomMessage(String message){
		Context context = AppController.getInstance().getApplicationContext();
		mToast=DrawerToast.getInstance(context);
		mToast.setDefaultBackgroundResource(R.drawable.common_toast_bg);
		mToast.show(message);
	}
}
