package com.ahsanzaman.contactsapp.ui.module.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Accolite- on 7/21/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    private ProgressDialog mProgressDialog;

    @Override
    public void showLoading() {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();
        } else if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(mProgressDialog !=null){
            mProgressDialog.hide();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getPresenter()!=null){
            getPresenter().onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(getPresenter()!=null){
            getPresenter().onStop();
        }
    }

    protected abstract BasePresenter getPresenter();


}
