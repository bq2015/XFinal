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

package com.bq2015.bqlib.widget;

import android.view.View;

import com.bq2015.bqlib.R;


/**
 * 功能：帮助切换错误，数据为空，正在加载的页面
 */
public class VaryViewHelper {
    /**
     * 切换不同视图的帮助类
     */
    OverlapViewHelper mViewHelper;
    /**
     * 错误页面
     */
    View mErrorView;
    /**
     * 正在加载页面
     */
    View mLoadingView;
    /**
     * 数据为空的页面
     */
    View mEmptyView;
    /**
     * 正在加载页面的进度环
     */
    ProgressWheel mLoadingProgress;


    public VaryViewHelper(View view) {
        this(new OverlapViewHelper(view));
    }

    public VaryViewHelper(OverlapViewHelper helper) {
        this.mViewHelper = helper;
    }


    void setUpEmptyView(View view) {
        mEmptyView = view;
        mEmptyView.setClickable(true);
    }

    void setUpErrorView(View view, View.OnClickListener listener) {
        mErrorView = view;
        mErrorView.setClickable(true);

        View btn = view.findViewById(R.id.vv_error_refresh);
        if (btn != null) {
            btn.setOnClickListener(listener);
        }
    }

    void setUpLoadingView(View view) {
        mLoadingView = view;
        mLoadingView.setClickable(true);
        mLoadingProgress = (ProgressWheel) view.findViewById(R.id.vv_loading_progress);
    }

    public void showEmptyView() {
        mViewHelper.showCaseLayout(mEmptyView);
        stopProgressLoading();
    }

    public void showErrorView() {
        mViewHelper.showCaseLayout(mErrorView);
        stopProgressLoading();
    }

    public void showLoadingView() {
        mViewHelper.showCaseLayout(mLoadingView);
        startProgressLoading();
    }

    public void showDataView() {
        mViewHelper.restoreLayout();
        stopProgressLoading();
    }


    private void stopProgressLoading() {
        if (mLoadingProgress != null && mLoadingProgress.isSpinning()) {
            mLoadingProgress.stopSpinning();
        }
    }

    private void startProgressLoading() {
        if (mLoadingProgress != null && !mLoadingProgress.isSpinning()) {
            mLoadingProgress.spin();
        }
    }

    public void releaseVaryView() {
        mErrorView = null;
        mLoadingView = null;
        mEmptyView = null;
    }

    public static class Builder {
        private View mErrorView;
        private View mLoadingView;
        private View mEmptyView;
        private View mDataView;
        private View.OnClickListener mRefreshListener;

        public Builder setErrorView(View errorView) {
            mErrorView = errorView;
            return this;
        }

        public Builder setLoadingView(View loadingView) {
            mLoadingView = loadingView;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            mEmptyView = emptyView;
            return this;
        }

        public Builder setDataView(View dataView) {
            mDataView = dataView;
            return this;
        }

        public Builder setRefreshListener(View.OnClickListener refreshListener) {
            mRefreshListener = refreshListener;
            return this;
        }

        public VaryViewHelper build() {
            VaryViewHelper helper = new VaryViewHelper(mDataView);
            if (mEmptyView != null) {
                helper.setUpEmptyView(mEmptyView);
            }
            if (mErrorView != null) {
                helper.setUpErrorView(mErrorView, mRefreshListener);
            }
            if (mLoadingView != null) {
                helper.setUpLoadingView(mLoadingView);
            }
            return helper;
        }
    }

}
