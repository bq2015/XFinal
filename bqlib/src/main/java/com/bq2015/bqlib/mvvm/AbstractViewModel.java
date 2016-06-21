package com.bq2015.bqlib.mvvm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.bq2015.bqlib.L;
import com.bq2015.bqlib.event.BQEventBusManager;
import com.bq2015.bqlib.event.BQSocketEventMsg;
import com.bq2015.bqlib.event.OnRegistBQSocketEventListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class AbstractViewModel<T extends IView>  implements OnRegistBQSocketEventListener {

    @Nullable
    private String mUniqueIdentifier;

    @Nullable
    private T mView;

    private boolean mBindViewWasCalled;

    void setUniqueIdentifier(@NonNull final String uniqueIdentifier) {
        mUniqueIdentifier = uniqueIdentifier;
    }

    /**
     *
     * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
     * change). This identifier will be reset in case the corresponding activity is killed.
     */
    @SuppressWarnings("unused")
    @Nullable
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    /**
     * Called when the ViewModel instance is created.
     * @param arguments initial ViewModel arguments passed from {@link Fragment#getArguments()}  or
     *                  {@link Activity#getIntent()}.{@link Intent#getExtras()}
     * @param savedInstanceState bundle with saved state, will be not null
     *               only in case the system is killed due to low memory
     *               and restored (and {@link #onSaveInstanceState(Bundle)} returned a non-null bundle.
     */
    @SuppressWarnings("EmptyMethod")
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {

    }

    /**
     * This method is an equivalent of {@link Fragment#onViewCreated(View, Bundle)} or {@link Activity#onCreate(Bundle)}.
     * At this point, the View is ready and you can initialise it.
     * @param view
     */
    public void onBindView(@NonNull T view) {
        mBindViewWasCalled = true;
        mView = view;
    }

    @Nullable
    public T getView() {
        return mView;
    }

    public void clearView() {
        mView = null;
    }

    @SuppressWarnings("EmptyMethod")
    public void onSaveInstanceState(@NonNull final Bundle bundle) {

    }

    @SuppressWarnings("EmptyMethod")
    public void onStop() {

    }

    @SuppressWarnings("EmptyMethod")
    public void onStart() {
        if (mView == null && !mBindViewWasCalled && L.isDebuggable()) {
            Log.e("AndroidViewModel", this.getClass().getSimpleName() + " - no view associated. You probably did not call setModelView() in your Fragment or Activity");
        }
    }

    /**
     * Called when there parent fragment or view is already gone and destroyed.
     * This is a good place to empty any planned tasks that are useless without a UI.
     */
    @SuppressWarnings("EmptyMethod")
    public void onDestroy() {
        BQEventBusManager.instance.unRegistEvent(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onYDSocketMsgReceived(BQSocketEventMsg event){

    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onYDSocketMsgReceivedAsync(BQSocketEventMsg event){

    }

    public void registSocketListener(){
        BQEventBusManager.instance.registEvent(this);
    }

}