package com.bq2015.xfinal.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bq2015.bqlib.event.RxBus;
import com.bq2015.bqlib.mvvm.base.BaseFragment;
import com.bq2015.xfinal.R;
import com.bq2015.xfinal.model.TestEvnet;
import com.bq2015.xfinal.viewmodel.MiddleFragmentVM;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: KyLin (SRS)
 * @date: 2016/8/18 10:50
 */
public class MiddleFragment extends BaseFragment<MiddleFragment, MiddleFragmentVM> {
    @Bind(R.id.mid_tv)
    TextView mMidTv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.getInstance().toObservable(TestEvnet.class)
                .filter(new Func1<TestEvnet, Boolean>() {
                    @Override
                    public Boolean call(TestEvnet testEvnet) {
                        mMidTv.setText("");
                        return "1".equals(testEvnet.getId());
                    }
                })
                .subscribe(new Action1<TestEvnet>() {
                    @Override
                    public void call(TestEvnet testEvnet) {
                        mMidTv.setText(testEvnet.getName());
                    }
                });

    }

    @Override
    protected int tellMeLayout() {
        return R.layout.fragment_middle;
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
    public Class<MiddleFragmentVM> getViewModelClass() {
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
}
