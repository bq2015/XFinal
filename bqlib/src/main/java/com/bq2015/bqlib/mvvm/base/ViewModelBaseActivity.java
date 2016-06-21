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

package com.bq2015.bqlib.mvvm.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bq2015.bqlib.R;
import com.bq2015.bqlib.StatusBarUtil;
import com.bq2015.bqlib.mvvm.AbstractViewModel;
import com.bq2015.bqlib.mvvm.IView;
import com.bq2015.bqlib.mvvm.ViewModelHelper;
import com.bq2015.bqlib.runtimepermission.PermissionsManager;
import com.bq2015.bqlib.widget.BaseLoadingViewHelper;
import com.bq2015.bqlib.widget.HttpNetLoadingViewHelper;
import com.bq2015.bqlib.widget.SwipeBackLayout;
import com.bq2015.bqlib.widget.VaryViewHelper;


@SuppressWarnings("all")
public abstract class ViewModelBaseActivity<T extends IView, RM extends AbstractViewModel<T>> extends ViewModelBaseEmptyActivity implements IView {

    private final ViewModelHelper<T, RM> mViewModeHelper = new ViewModelHelper<>();
    protected Context mContext;
    protected VaryViewHelper mVaryViewHelper;
    private SwipeBackLayout swipeBackLayout;
    protected HttpNetLoadingViewHelper httpNetLoadingViewHelper;

    public HttpNetLoadingViewHelper getLoadingViewHelper() {
        if(httpNetLoadingViewHelper == null){
            httpNetLoadingViewHelper = new BaseLoadingViewHelper(this);
        }
        return httpNetLoadingViewHelper;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(this, savedInstanceState, getViewModelClass(), getIntent().getExtras());
        mContext = this;
        setContentView(tellMeLayout());
        ActivityManager.getAppManager().addActivity(this);
        //bundle
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        //initView ok
        initView(savedInstanceState);
        //varyView
        if (getStatusTargetView() != null) {
            mVaryViewHelper = new VaryViewHelper.Builder()
                    .setDataView(getStatusTargetView())
                    .setLoadingView(LayoutInflater.from(mContext).inflate(R.layout.layout_loadingview, null))
                    .setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.layout_emptyview, null))
                    .setErrorView(LayoutInflater.from(mContext).inflate(R.layout.layout_errorview, null))
                    .setRefreshListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRetryListener();
                        }
                    })
                    .build();
        }
        //statusBar
//        setStatusBar();
        setModelView((T)this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (isBackLayout()) {
            super.setContentView(getContainer());
            View backLayoutRootView = LayoutInflater.from(this).inflate(tellMeLayout(), null);
            backLayoutRootView.setBackgroundColor(getResources().getColor(R.color.window_background));
            swipeBackLayout.addView(backLayoutRootView);
        } else {
            super.setContentView(layoutResID);
        }
    }

    /**
     * need drag back
     */
    protected boolean isBackLayout() {
        return false;
    }

    protected abstract void getBundleExtras(@NonNull Bundle extras);

    protected void setStatusBar() {
        StatusBarUtil.setTranslucent(this);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int tellMeLayout();

    /**
     * Rewrite this method defines the network status view
     * @return
     */
    protected abstract View getStatusTargetView();

    /**
     * when set getStatusTargetView,use under error status
     */
    protected void onRetryListener() {

    }


    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        final ImageView ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.theme_black_7f));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fa, float fs) {
                ivShadow.setAlpha(1 - fs);
            }
        });
        return container;
    }

    /**
     * Call this after your view is ready - usually on the end of {@link android.app.Activity#onCreate(Bundle)}
     *
     * @param view view
     */
    @SuppressWarnings("unused")
    public final void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    public abstract Class<RM> getViewModelClass();

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        if (mVaryViewHelper != null) mVaryViewHelper.releaseVaryView();
        ActivityManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @SuppressWarnings("unused")
    @NonNull
    public final RM getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    @SuppressWarnings("all")
    protected final <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    /**
     * startActivity
     */
    final public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     */
    final public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     */
    final public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     */
    final public void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     */
    final public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     */
    final public void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
