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

package com.bq2015.bqlib.mvvm;

import com.bq2015.oknet.modeinterface.INetView;

public interface IView extends INetView {

    void showErrorView();
    void showEmptyView();
    void showDataView();

}
