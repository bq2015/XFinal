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

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/4/18
 ************************************************************/
public interface HttpNetLoadingViewHelper {

    /**
     * 加载中状态显示和隐藏,设置LoadingText
     */
    void showLoadingView();
    void dismissLoadingView();
    void setLoadingText(String loadingText);
    void clearView();
}
