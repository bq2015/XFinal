package com.bq2015.xfinal.vm;

import android.support.annotation.NonNull;

import com.bq2015.bqlib.mvvm.AbstractViewModel;
import com.bq2015.xfinal.view.activity.MainActivity;

/**
 *
 * Created by bq2015 on 2016/6/24.
 */
public class MainActivityVM extends AbstractViewModel<MainActivity> {

    private MainActivity mView;

    @Override
    public void onBindView(@NonNull MainActivity view) {
        super.onBindView(view);
        mView = getView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
