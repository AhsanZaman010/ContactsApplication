package com.ahsanzaman.contactsapp.ui.module.contact.view.add;

import android.content.DialogInterface;
import android.os.Bundle;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.add.AddContactPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.EditableContactActivity;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/25/2017.
 */

public class AddContactActivity extends EditableContactActivity implements AddContactView {

    @Inject
    AddContactPresenter mAddContactPresenter;

    @Override
    protected void onValidatedSave() {
        mAddContactPresenter.onSave();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mAddContactPresenter;
    }

    @Override
    protected void retry() {
        mAddContactPresenter.onSave();
    }

    @Override
    protected void onCancelErrorDialog(DialogInterface dialog) {
        dialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.add_contact_title));
        ((ContactsApplication) getApplicationContext()).getDeps().plus(new ContactsModule(this)).inject(this);
    }

    @Override
    public ContactDetail getContactDetail() {
        return getContactDetailResponse();
    }

    @Override
    public void finishOnSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
