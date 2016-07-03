package com.bq2015.xfinal.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bq2015.bqlib.mvvm.base.BaseActivity;
import com.bq2015.xfinal.R;
import com.bq2015.xfinal.vm.MainActivityVM;

public class MainActivity extends BaseActivity<MainActivity,MainActivityVM> {


    private MainActivityVM mViewModel;
    private TextView mText;

    @Override
    protected void getBundleExtras(@NonNull Bundle extras) {

    }



    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewModel = getViewModel();
        mText = (TextView) findViewById(R.id.tv_mainui);
        //CategoryBeans result = JSON.parseObject(Constant.STR.JSONTEST, CategoryBeans.class);
        //mText.setText(result.getRetcode());
    }

    /**
     * Textview显示内容
     * （第二步：更新UI控件的方法）
     * @param content
     */
    public void setTextContent(String content) {
        mText.setText(content);
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
        return MainActivityVM.class;
    }
}
