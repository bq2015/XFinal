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

package com.bq2015.bqlib.mvvm.base;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;

import com.bq2015.bqlib.R;
import com.bq2015.bqlib.StatusBarUtil;
import com.bq2015.bqlib.mvvm.AbstractViewModel;
import com.bq2015.bqlib.mvvm.IView;

import butterknife.ButterKnife;

/**
 * @author bq2015 on 2016/5/20.
 */
public abstract class BaseActivity<T extends IView, VM extends AbstractViewModel<T>> extends ViewModelBaseActivity<T, VM> {

    protected Context context = null;
    protected Activity activity = null;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        context = this;
        activity = this;
    }


    @Override
    protected void setStatusBar() {
            StatusBarUtil.setColor(this, R.color.toast_bg_color);
    }

    @Override
    public void onDestroy() {
        nethandler.removeCallbacksAndMessages(null);
        nethandler = null;
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getLoadingViewHelper().clearView();
    }


    Handler nethandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String text = null;
                    if(msg.obj == null){
                        text = getString(R.string.lib_loading);
                    }else {
                        text = (String) msg.obj;
                    }
                    getLoadingViewHelper().setLoadingText(text);
                    getLoadingViewHelper().showLoadingView();
                    break;
                case 1:
                    getLoadingViewHelper().dismissLoadingView();
                    break;
            }
        }
    };

    public void showLoadingView(String showText){
        Message message = Message.obtain();
        message.what = 0;
        message.obj = showText;
        nethandler.sendMessage(message);
    }
    public void dissmissLoadingView(){
        nethandler.sendEmptyMessage(1);
    }

    public void showErrorView(){
        mVaryViewHelper.showErrorView();
    }
    public void showEmptyView(){
        mVaryViewHelper.showEmptyView();
    }
    public void showDataView(){
        mVaryViewHelper.showDataView();
    }

}
