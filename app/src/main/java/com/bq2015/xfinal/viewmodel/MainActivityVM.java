package com.bq2015.xfinal.viewmodel;

import android.support.annotation.NonNull;

import com.bq2015.bqhttp.event.BQNetEvent;
import com.bq2015.bqhttp.net.NetRequest;
import com.bq2015.bqhttp.net.OnBQNetEventListener;
import com.bq2015.bqlib.mvvm.AbstractViewModel;
import com.bq2015.xfinal.model.BaseNetList;
import com.bq2015.xfinal.model.DishCategory;
import com.bq2015.xfinal.model.StockInfo;
import com.bq2015.xfinal.request.Net;
import com.bq2015.xfinal.view.activity.MainActivity;

/**
 * ViewModel层  业务逻辑
 * Created by bq2015 on 2016/6/24.
 */
public class MainActivityVM extends AbstractViewModel<MainActivity> {

    private MainActivity mView;
    private NetRequest<BaseNetList<DishCategory>> mBaidu;
    private NetRequest<StockInfo> mStockInfoNetRequest;

    @Override
    public void onBindView(@NonNull MainActivity view) {
        super.onBindView(view);
        mView = getView();
    }

    @Override
    public void onStart() {
        super.onStart();
        /*mBaidu = Net.get().dishCategories(1).execute(new OnBQNetEventListener() {
            @Override
            public void netRequestSuccess(BQNetEvent event) {
                if (event.whoEqual(mBaidu)) {
                   // BaseNetList<DishCategory> base = event.getNetResult();
                  //  List<DishCategory> dishCategories = base.getList();
                  //  mView.setTextContent(dishCategories.get(0).getName());
                }
            }

            @Override
            public boolean netRequestFail(BQNetEvent event) {
                return false;
            }
        });
*/
        /**
         * 第三步：在VM层，调用请求结果，返回Bean，调用V层更新控件的就读，通实方法参数传递Bean中封装好数据信息
         */
        mStockInfoNetRequest = Net.get()
                .getStockInfos("b67e5efb0c78439e964c83a1ee752f4c", "hs002230", 1, "JSON", true)//网络接口
                .showProgress(mView,"正在加载...")//加载中状态
                .execute(new OnBQNetEventListener() { //执行并返回Bean
                    @Override
                    public void netRequestSuccess(BQNetEvent event) {
                        if (event.whoEqual(mStockInfoNetRequest)) {
                            mView.dissmissLoadingView();
                            StockInfo info = event.getNetResult();
                            //调用View层方法更新控件
                            mView.setTextContent(info.getResult().getMarket().getHSI().getName());
                        }
                    }

                    @Override
                    public boolean netRequestFail(BQNetEvent event) {
                        return false;
                    }
                });
    }

}
