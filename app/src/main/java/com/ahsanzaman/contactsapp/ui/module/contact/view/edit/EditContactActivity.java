package com.ahsanzaman.contactsapp.ui.module.contact.view.edit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.di.module.EditContactModule;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
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
    private ContactDetailResponse mContactDetailResponse;

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
            mContactDetailResponse = getIntent().getParcelableExtra(CONTACT_DETAIL);
        }
        if (mContactDetailResponse == null) {
            Toast.makeText(this, R.string.contact_not_found, Toast.LENGTH_SHORT);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        } else {
            ((ContactsApplication) getApplicationContext()).getDeps().plus(new EditContactModule(this, mContactDetailResponse)).inject(this);
            bind(mContactDetailResponse);
        }
    }

    @Override
    public void bindToContact(ContactDetailResponse contactDetailResponse) {
        bind(mContactDetailResponse);
    }

    @Override
    public ContactDetailResponse getContactDetail() {
        return getContactDetailResponse();
    }

    @Override
    public void finishOnSuccess(ContactDetailResponse contactDetailResponse) {
        if(getIntent()!=null) {
            setResult(RESULT_OK);
            getIntent().putExtra(CONTACT_DETAIL, contactDetailResponse);
        }
        finish();
    }
}
