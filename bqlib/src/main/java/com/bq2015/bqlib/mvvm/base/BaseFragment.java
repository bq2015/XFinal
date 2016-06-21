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
import android.view.View;

import com.bq2015.bqlib.mvvm.AbstractViewModel;
import com.bq2015.bqlib.mvvm.IView;

import butterknife.ButterKnife;


/**
 * @author bq2015 on 2016/5/20.
 */
public abstract class BaseFragment<T extends IView, R extends AbstractViewModel<T>> extends ViewModelBaseFragment<T, R> {
    protected Context context = null;
    protected Activity activity = null;
    @Override
    protected void injectView(View mRootView) {
        ButterKnife.bind(this, mRootView);
        context = getActivity();
        activity = getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
                        text = getString(com.bq2015.bqlib.R.string.lib_loading);
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
