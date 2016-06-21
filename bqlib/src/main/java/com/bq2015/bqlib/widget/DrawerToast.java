/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqlib.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 抽屉吐司
 * 通过getInstance(context)获得本类的单例
 * @author hushicheng
 *
 */
public class DrawerToast {
	/**
	 * 当前Android系统版本
	 */
	static int currentapiVersion=android.os.Build.VERSION.SDK_INT;
	
	/**
	 * 入场动画持续时间
	 */
	private static final int TIME_START_ANIM = 500;
	/**
	 * 离场动画持续时间
	 */
	private static final int TIME_END_ANIM = 500;
	/**
	 * 每条Toast显示持续时间
	 */
	private static final int TIME_DURATION = 2500;
	
	/**
	 * 单例
	 */
	private static DrawerToast instance;
	
	/**
	 * 获得单例
	 * @param context
	 * @return
	 */
	public static DrawerToast getInstance(Context context){
		if(instance == null){
			instance = new DrawerToast(context);
		}
		return instance;
	}
	
	private DrawerToast(Context context){
		if(context == null || context.getApplicationContext() == null) {
			throw new NullPointerException("context can't be null");
		}
    	mContext = context.getApplicationContext();
    	initView();
		initTN();
		
		//防反射获取实例
		if(instance != null)throw new NullPointerException("error");
	}
	
	/**
	 * UI线程句柄
	 */
	Handler mHandler;
	
	long mStartTime=0;
	
	/**
	 * 内容对象
	 */
	Context mContext;
    
	/**
	 * 顶层布局
	 */
	LinearLayout mTopView, mTopView2;
	
	/**
	 * 内容布局
	 */
    LinearLayout mView;
    
    /**
     * 提示文字TextView
     */
    TextView mMessageTv;
    
    /**
     * 布局属性
     */
    LayoutParams lp_WW, lp_MM;
    
    /**
     * 屏幕宽度
     */
    int screenWidth;
    /**
     * 屏幕高度
     */
    int screenHeight;
    
    /**
     * 默认背景的resid
     */
    Integer defaultBackgroundResid;
    
    /**
     * 默认背景的颜色
     */
    Drawable defaultBackgroundColor;
    
    /**
     * 默认文字颜色
     */
    int defaultTextColor;
    
    /**
     * 反射过程中是否出现异常的标志
     */
    boolean hasReflectException = false;
    
    Runnable runnable;
    
    /**
     * 初始化视图控件
     */
    private void initView(){
    	mHandler = new Handler(mContext.getMainLooper());

    	lp_WW = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	lp_MM = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	
    	DisplayMetrics mDisplayMetrics = mContext.getResources().getDisplayMetrics();
    	screenWidth = mDisplayMetrics.widthPixels;
    	screenHeight = mDisplayMetrics.heightPixels;

    	mTopView = new LinearLayout(mContext);
    	mTopView.setLayoutParams(lp_MM);
    	mTopView.setOrientation(LinearLayout.VERTICAL);
    	mTopView.setGravity(Gravity.CENTER);
    	
    	mTopView2 = new LinearLayout(mContext);
    	LayoutParams params = new LayoutParams(screenWidth, screenHeight);
    	mTopView2.setLayoutParams(params);
    	mTopView2.setOrientation(LinearLayout.VERTICAL);
    	mTopView2.setGravity(Gravity.BOTTOM);
    	
    	mView = new LinearLayout(mContext);
    	mView.setLayoutParams(lp_MM);
    	mView.setOrientation(LinearLayout.VERTICAL);
    	mView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
    	
    	View gapView = new View(mContext);
    	gapView.setLayoutParams(new LayoutParams(screenWidth, screenHeight/4));
    	mView.addView(gapView);
    	
    	mTopView.addView(mTopView2);
    	mTopView2.addView(mView);
    	
    	resetDefaultBackgroundAndTextColor();
    }
    
    /**
     * 显示一条Toast
     * @param msg 消息内容
     */
    public void show(String msg){
    	if (mMessageTv == null) {
    		show(msg, null, null, null);
		}
    	else{
    		mMessageTv.setText(msg);
    		
			if (runnable==null) {
				runnable=new Runnable() {
					@Override
					public void run() {
						hide(mMessageTv, getEndAnimation());
					}
				};
			}
			//调用Handler的removeCallbacks()方法，删除队列当中未执行的线程对象  
			mHandler.removeCallbacks(runnable);
			
			long executedTime;
			if (mStartTime == 0) {
				executedTime=0;
			}else{
				executedTime=System.currentTimeMillis()-mStartTime;
			}
			long showTime=TIME_DURATION;
			
			//延迟后隐藏传入toast
	    	mHandler.postDelayed(runnable, showTime-executedTime);
	    	mStartTime=System.currentTimeMillis();
		}
    }
  
    /**
     * 显示一条Toast
     * @param msg 消息内容
     * @param duration 持续时间，单位为毫秒
     */
    public void show(String msg, long duration){
    	if (mMessageTv == null) {
    		show(msg, duration, null, null);
		}
    	else{
    		mMessageTv.setText(msg);
    		
			if (runnable==null) {
				runnable=new Runnable() {
					@Override
					public void run() {
						hide(mMessageTv, getEndAnimation());
					}
				};
			}
			//调用Handler的removeCallbacks()方法，删除队列当中未执行的线程对象  
			mHandler.removeCallbacks(runnable);
			
			long executedTime;
			if (mStartTime == 0) {
				executedTime=0;
			}else{
				executedTime=System.currentTimeMillis()-mStartTime;
			}
			long showTime=duration<=0?TIME_DURATION:duration;
			
			//延迟后隐藏传入toast
	    	mHandler.postDelayed(runnable, showTime-executedTime);
	    	mStartTime=System.currentTimeMillis();
		}
    }

    /**
     * 显示一条Toast
     * @param msg 消息内容
     * @param duration 持续时间，单位为毫秒
     * @param startAnim 入场动画
     * @param endAnim 离场动画
     */
    public void show(String msg, Long duration, Animation startAnim, final Animation endAnim){
    	if (mMessageTv == null) {
    		mMessageTv=getTextView(msg);
		}
    	mMessageTv.setText(msg);
    	
    	//反射过程异常时则使用源生Toast
    	if(hasReflectException){
    		Toast t = new Toast(mContext);
    		t.setView(mMessageTv);
    		t.setDuration(Toast.LENGTH_SHORT);
    		t.show();
    		//重新获取反射对象
    		initTN();
    		return;
    	}
    	
    	//显示顶层容器控件
    	if(mView.getChildCount() == 1)showToast();
    	
    	//获得入场动画
    	if(startAnim == null)startAnim = getStartAnimation();
    	mMessageTv.clearAnimation();
    	mMessageTv.startAnimation(startAnim);
    	//把传入的toast显示出来
		mView.addView(mMessageTv, 0);
		
		if (runnable==null) {
			runnable=new Runnable() {
				@Override
				public void run() {
					hide(mMessageTv, endAnim);
				}
			};
		}
		//调用Handler的removeCallbacks()方法，删除队列当中未执行的线程对象  
		mHandler.removeCallbacks(runnable);
		
		long executedTime;
		if (mStartTime == 0) {
			executedTime=0;
		}else{
			executedTime=System.currentTimeMillis()-mStartTime;
		}
		long showTime=duration==null?TIME_DURATION:duration;
		
		//延迟后隐藏传入toast
    	mHandler.postDelayed(runnable, showTime-executedTime);
    	mStartTime=System.currentTimeMillis();
    }
    
    /**
     * 设置默认背景颜色
     * @param color 颜色值
     * @param alpha 透明度
     */
    public void setDefaultBackgroundColor(int color, Integer alpha){
    	defaultBackgroundColor = new ColorDrawable(color);
    	if(alpha!=null)defaultBackgroundColor.setAlpha(alpha);
    	defaultBackgroundResid = null;
    }
    
    /**
     * 设置默认背景资源
     * @param resid 图片资源文件
     */
    public void setDefaultBackgroundResource(int resid){
    	defaultBackgroundResid = resid;
    }
    
    /**
     * 设置默认文字颜色
     * @param color
     */
    public void setDefaultTextColor(int color){
    	defaultTextColor = color;
    }
    
    /**
     * 重置背景和文字颜色
     */
    public void resetDefaultBackgroundAndTextColor(){
    	defaultTextColor = Color.WHITE;
    	defaultBackgroundColor = new ColorDrawable(Color.BLACK);
    	defaultBackgroundColor.setAlpha(200);
    	defaultBackgroundResid = null;
    }
    
    /**
     * 隐藏指定控件
     * @param v 需要隐藏的控件
     * @param endAnim 结束动画
     */
    public final void hide(final View v, Animation endAnim){
    	if(v == null || mView.indexOfChild(v) < 0) return;
    	
    	//获得出场动画
    	if(endAnim == null)endAnim = getEndAnimation();
    	v.clearAnimation();
    	//开始出场动画
    	v.startAnimation(endAnim);
    	
    	//动画结束后移除控件
    	mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
		    	if(v == null || mView.indexOfChild(v) < 0) return;
		    	mStartTime=0;
		    	//移除指定控件
				mView.removeView(v);
		    	mMessageTv=null;
				//隐藏顶层容器控件
				if(mView.getChildCount()==1)hideToast();
			}
		}, TIME_END_ANIM);
    }
    
    /**
     * 获得一个设置好属性的TextView
     * @param msg
     * @return
     */
	public TextView getTextView(String msg) {
		TextView tv = new TextView(mContext);
		tv.setLayoutParams(lp_WW);
		tv.setText(msg);
		tv.setTextColor(defaultTextColor);
		Drawable background = null;
		if (defaultBackgroundResid != null) {
			background = mContext.getResources().getDrawable(
					defaultBackgroundResid);
		} else {
			background = defaultBackgroundColor;
		}
		if (currentapiVersion >= 16)
			tv.setBackground(background);
		else
			tv.setBackgroundResource(defaultBackgroundResid);
		
		tv.setPadding(13, 15, 13, 15);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
    
    /**
     * 获得入场动画
     * @return
     */
    protected Animation getStartAnimation(){
    	AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
    	animAlpha.setDuration(TIME_START_ANIM);
    	animAlpha.setFillAfter(true);

    	AnimationSet sets = new AnimationSet(true);
    	sets.addAnimation(animAlpha);
    	
    	return sets;
    }
    
    /**
     * 获得离场动画
     * @return
     */
    protected Animation getEndAnimation(){
    	AlphaAnimation animAlpha = new AlphaAnimation(1, 0);
    	animAlpha.setDuration(TIME_END_ANIM);
    	animAlpha.setFillAfter(true);
    	
    	AnimationSet sets = new AnimationSet(true);
    	sets.addAnimation(animAlpha);
    	
    	return sets;
    }
    
    /* 以下为反射相关内容 */
	Toast mToast;
    Field mTN;
    Object mObj;
    Method showMethod, hideMethod;
    
    /**
     * 通过反射获得mTN下的show和hide方法
     */
    private void initTN(){
    	mToast = new Toast(mContext);
    	mToast.setView(mTopView);
    	Class<Toast> clazz = Toast.class;
    	try {
			mTN = clazz.getDeclaredField("mTN");
			mTN.setAccessible(true);
			mObj = mTN.get(mToast);

			Class<?>[] temp=null;
            showMethod = mObj.getClass().getDeclaredMethod("show", temp);
            hideMethod = mObj.getClass().getDeclaredMethod("hide", temp);
            hasReflectException = false;
		} catch (NoSuchFieldException e) {
			hasReflectException = true;
			System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			hasReflectException = true;
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			hasReflectException = true;
			System.out.println(e.getMessage());
		} catch (NoSuchMethodException e) {
			hasReflectException = true;
			System.out.println(e.getMessage());
		}
    }
    /**
     * 通过反射获得的show方法显示指定View
     */
    private void showToast(){
        try {  
        	//高版本需要再次手动设置mNextView属性，2系列版本不需要
        	if(currentapiVersion > 10){
        		Field mNextView = mObj.getClass().getDeclaredField("mNextView");
        		mNextView.setAccessible(true);
        		mNextView.set(mObj, mTopView);
        	}

        	Object[] temp=null;
        	showMethod.invoke(mObj, temp);
            hasReflectException = false;
        } catch (Exception e) {
			hasReflectException = true;
			System.out.println(e.getMessage());
        }
    }
    /**
     * 通过反射获得的hide方法隐藏指定View
     */
    private void hideToast() {
        try {
        	Object[] temp=null;
            hideMethod.invoke(mObj, temp);
            hasReflectException = false;
        } catch (Exception e) {
			hasReflectException = true;
			System.out.println(e.getMessage());
        }
    }
}