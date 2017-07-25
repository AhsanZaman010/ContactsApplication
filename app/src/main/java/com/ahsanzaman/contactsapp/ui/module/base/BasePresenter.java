package com.ahsanzaman.contactsapp.ui.module.base;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.ahsanzaman.contactsapp.model.response.ResponeObject;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class BasePresenter implements RemoteServiceCallback {

    private final BaseView mView;
    protected CompositeDisposable mCompositeDisposable;
    private final String TAG = getClass().getSimpleName();

    protected BasePresenter(BaseView view){
        mView = view;
    }

    void onStart(){
        mCompositeDisposable = new CompositeDisposable();
    }

    void onStop(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        if(responseObject instanceof ResponeObject){
            mView.hideLoading();
            mView.showError(((ResponeObject) responseObject).getStatus());
        }
    }

    @Override
    public void onError(NetworkErrorException networkError, int requestCode) {
        mView.hideLoading();
        if(networkError!=null){
            mView.showError(networkError.getMessage());
            Log.e(TAG, "Error received: "+ networkError.getMessage());
        } else {
            Log.e(TAG, "Error received");
            mView.showError("");
        }
    }
}
