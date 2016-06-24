package com.bq2015.xfinal.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.bq2015.bqlib.mvvm.base.BaseActivity;
import com.bq2015.xfinal.R;
import com.bq2015.xfinal.vm.MainActivityVM;

public class MainActivity extends BaseActivity<MainActivity,MainActivityVM> {


    private MainActivityVM mViewModel;

    @Override
    protected void getBundleExtras(@NonNull Bundle extras) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewModel = getViewModel();
    }

    @Override
    protected int tellMeLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View getStatusTargetView() {
        return null;
    }

    @Override
    public Class getViewModelClass() {
        return null;
    }
}
