package com.bq2015.xfinal.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bq2015.bqlib.mvvm.base.BaseActivity;
import com.bq2015.xfinal.R;
import com.bq2015.xfinal.view.fragment.MiddleFragment;
import com.bq2015.xfinal.view.fragment.TopFragment;
import com.bq2015.xfinal.viewmodel.MainActivityVM;
import com.bq2015.xfinal.view.fragment.DownFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainActivity, MainActivityVM> {


    @Bind(R.id.main_fl_top)
    FrameLayout mMainFlTop;
    @Bind(R.id.main_fl_middle)
    FrameLayout mMainFlMiddle;
    @Bind(R.id.main_fl_down)
    FrameLayout mMainFlDown;
    private MainActivityVM mViewModel;
    private TextView mText;

    @Override
    protected void getBundleExtras(@NonNull Bundle extras) {

    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewModel = getViewModel();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_fl_top,new TopFragment());
        ft.add(R.id.main_fl_middle,new MiddleFragment());
        ft.add(R.id.main_fl_down,new DownFragment());
        ft.commit();


    }

    /**
     * Textview显示内容
     * （第二步：更新UI控件的方法）
     *
     * @param content
     */
    public void setTextContent(String content) {
//        mText.setText(content);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
