package com.ahsanzaman.contactsapp.ui.module.contact.view.edit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.EditContactModule;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit.EditContactPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.EditableContactActivity;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/25/2017.
 */

public class EditContactActivity extends EditableContactActivity implements EditContactView {

    private static final String CONTACT_DETAIL = "Contact detail";
    @Inject
    EditContactPresenter mPresenter;
    private ContactDetail mContactDetail;

    @Override
    protected void onValidatedSave() {
        mPresenter.onSave();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void retry() {
        mPresenter.onSave();
    }

    @Override
    protected void onCancelErrorDialog(DialogInterface dialog) {
        dialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.edit_contact_title));
        if (getIntent() != null) {
            mContactDetail = getIntent().getParcelableExtra(CONTACT_DETAIL);
        }
        if (mContactDetail == null) {
            Toast.makeText(this, R.string.contact_not_found, Toast.LENGTH_SHORT);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        } else {
            ((ContactsApplication) getApplicationContext()).getDeps().plus(new EditContactModule(this, mContactDetail)).inject(this);
            bind(mContactDetail);
        }
    }

    @Override
    public void bindToContact(ContactDetail contactDetail) {
        bind(mContactDetail);
    }

    @Override
    public ContactDetail getContactDetail() {
        return getContactDetailResponse();
    }

    @Override
    public void finishOnSuccess(ContactDetail contactDetail) {
        if(getIntent()!=null) {
            setResult(RESULT_OK);
            getIntent().putExtra(CONTACT_DETAIL, contactDetail);
        }
        finish();
    }
}
