package com.bq2015.xfinal.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq2015.bqlib.event.RxBus;
import com.bq2015.bqlib.mvvm.base.BaseFragment;
import com.bq2015.xfinal.R;
import com.bq2015.xfinal.model.TestEvnet;
import com.bq2015.xfinal.viewmodel.TopFragmentVM;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: KyLin (SRS)
 * @date: 2016/8/18 10:47
 */
public class TopFragment extends BaseFragment<TopFragment, TopFragmentVM> {

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int tellMeLayout() {
        return R.layout.fragment_top;
    }

    @Override
    protected View getStatusTargetView() {
        return null;
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Nullable
    @Override
    public Class<TopFragmentVM> getViewModelClass() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fg_btn_mid, R.id.fg_btn_down, R.id.fg_btn_mid_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_btn_mid:
                RxBus.getInstance().postEvent(new TestEvnet("1","来自中按钮"));
                break;
            case R.id.fg_btn_down:
                RxBus.getInstance().postEvent(new TestEvnet("2","来自下按钮"));
                break;
            case R.id.fg_btn_mid_down:
                RxBus.getInstance().postEvent(new TestEvnet("3","来自同时按钮"));
                break;
        }
    }
}
