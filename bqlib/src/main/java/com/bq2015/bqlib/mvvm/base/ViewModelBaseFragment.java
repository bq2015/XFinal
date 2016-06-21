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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq2015.bqlib.R;
import com.bq2015.bqlib.mvvm.AbstractViewModel;
import com.bq2015.bqlib.mvvm.IView;
import com.bq2015.bqlib.mvvm.ViewModelHelper;
import com.bq2015.bqlib.runtimepermission.PermissionsManager;
import com.bq2015.bqlib.widget.BaseLoadingViewHelper;
import com.bq2015.bqlib.widget.HttpNetLoadingViewHelper;
import com.bq2015.bqlib.widget.VaryViewHelper;

import java.lang.reflect.Field;


@SuppressWarnings("all")
public abstract class ViewModelBaseFragment<T extends IView, RM extends AbstractViewModel<T>> extends Fragment implements IView {

    public static String mTag = null;
    private final ViewModelHelper<T, RM> mViewModeHelper = new ViewModelHelper<>();
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    protected View mRootView;

    public View getRootView() {
        return mRootView;
    }

    protected VaryViewHelper mVaryViewHelper;

    protected Context mContext;
    protected HttpNetLoadingViewHelper httpNetLoadingViewHelper;

    public HttpNetLoadingViewHelper getLoadingViewHelper() {
        if(httpNetLoadingViewHelper == null){
            httpNetLoadingViewHelper = new BaseLoadingViewHelper(getActivity());
        }
        return httpNetLoadingViewHelper;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mTag = this.getClass().getSimpleName();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(getActivity(), savedInstanceState, getViewModelClass(), getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (tellMeLayout() != 0) {
            return inflater.inflate(tellMeLayout(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRootView = view;
        injectView(mRootView);
        //varyView
        initView(savedInstanceState);

        if (getStatusTargetView() != null) {
            mVaryViewHelper = new VaryViewHelper.Builder()
                    .setDataView(getStatusTargetView())//如果根部局无效，套一层父布局即可
//                    .setLoadingView(LayoutInflater.from(mContext).inflate(R.layout.layout_loadingview, null))
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
        setModelView((T)this);
        initPrepare();
    }

    protected void injectView(View mRootView) {
        //ButterKnife
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int tellMeLayout();

    protected abstract View getStatusTargetView();

    protected abstract void onFirstUserVisible();

    protected abstract void onUserVisible();

    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    protected abstract void onUserInvisible();

    @SuppressWarnings("all")
    protected final <T extends View> T getView(int id) {
        return (T) mRootView.findViewById(id);
    }

    /**
     * when set getStatusTargetView,use under error status
     */
    protected void onRetryListener() {

    }

    @Nullable
    public abstract Class<RM> getViewModelClass();

    /**
     * Call this after your view is ready - usually on the end of {@link Fragment#onViewCreated(View, Bundle)}
     *
     * @param view view
     */
    protected void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

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
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    public void onDestroyView() {
        mViewModeHelper.onDestroyView(this);
        if (mVaryViewHelper != null) mVaryViewHelper.releaseVaryView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @NonNull
    @SuppressWarnings("unused")
    public RM getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    final protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    final protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    final protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    final protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
