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

import com.bq2015.bqlib.R;


/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/4/18
 ************************************************************/
public class BaseLoadingViewHelper implements HttpNetLoadingViewHelper {
    TipDialog mTipdialog;
    int numberOfRequest = 0;

    public BaseLoadingViewHelper(Context context){
        init(context);
    }

    public void init(Context context) {
        mTipdialog = new TipDialog(context, TipDialog.TYPE_PB);
        mTipdialog.setTipText(context.getString(R.string.lib_loading));
        mTipdialog.setCanceledOnTouchOutside(false);
    }

    public void setLoadingText(String loadingText){
        mTipdialog.setTipText(loadingText);
    }
    @Override
    public void showLoadingView() {
        if(numberOfRequest == 0){
            try {
                mTipdialog.show();
                mTipdialog.setTagIvAnimation();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        numberOfRequest++;
    }

    @Override
    public void dismissLoadingView() {
        numberOfRequest--;
        if(numberOfRequest<0)
            numberOfRequest = 0;
        if(numberOfRequest == 0&&mTipdialog.isShowing()){
            try {
                mTipdialog.dismiss();
                mTipdialog.cancleAnimation();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void clearView() {
        if(mTipdialog!=null&&mTipdialog.isShowing())
            mTipdialog.dismiss();
    }

}
