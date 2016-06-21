/*
 *    Copyright (c) 2016, lyao. lomoliger@hotmail.com
 *
 *     Part of the code from the open source community,
 *     thanks stackOverflow & gitHub .
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.bq2015.bqlib.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 功能：切换布局，用一个新的View替换原先的View
 */
public class ReplaceViewHelper implements ICaseViewHelper {

    public View mDataView;
    public View mCurrentView;

    public ViewGroup mParentView;
    public ViewGroup.LayoutParams mLayoutParams;
    public int mViewIndex;

    public ReplaceViewHelper(View dataView) {

        /*记录显示数据的View*/
        this.mDataView = dataView;

        mLayoutParams = dataView.getLayoutParams();

        /*记录父View*/
        if (dataView.getParent() != null) {
            mParentView = (ViewGroup) dataView.getParent();
        } else {
            mParentView = (ViewGroup) dataView.getRootView();
        }

        /*记录要显示的View在父View中的位置*/
        int childCount = mParentView.getChildCount();
        for (int index = 0; index < childCount; index++) {
            if (dataView == mParentView.getChildAt(index)) {
                mViewIndex = index;
                break;
            }
        }

        this.mCurrentView = dataView;

    }


    @Override
    public Context getContext() {
        return mDataView.getContext();
    }

    @Override
    public View getDataView() {
        return mDataView;
    }

    @Override
    public View getCurrentView() {
        return mCurrentView;
    }

    @Override
    public void restoreLayout() {
        showCaseLayout(mDataView);
    }

    @Override
    public void showCaseLayout(View view) {
        if (view == null) {
            return;
        }
        this.mCurrentView = view;

        /*如果要显示的View跟已显示View一样，就不用切换了*/
        if (mParentView.getChildAt(mViewIndex) != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            /*切换的时候，先移除原先显示的View,再显示需要的View*/
            mParentView.removeViewAt(mViewIndex);
            mParentView.addView(view, mViewIndex, mLayoutParams);

        }

    }

    @Override
    public void showCaseLayout(int layoutId) {
        showCaseLayout(inflate(layoutId));
    }

    @Override
    public View inflate(int layoutId) {
        return LayoutInflater.from(mDataView.getContext()).inflate(layoutId, null);
    }
}
