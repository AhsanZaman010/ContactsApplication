package com.ahsanzaman.contactsapp.ui.module.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class BasePresenter {

    protected CompositeDisposable mCompositeDisposable;

    void onStart(){
        mCompositeDisposable = new CompositeDisposable();
    }

    void onStop(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.dispose();
        }
    }

}
