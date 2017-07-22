package com.ahsanzaman.contactsapp.module.contacts.view;

import android.os.Bundle;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.module.contacts.presenter.ContactsPresenter;

import javax.inject.Inject;

public class ContactsActivity extends BaseActivity {


    @Inject
    ContactsPresenter mContactsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ContactsApplication) getApplicationContext()).getDeps().plus(new ContactsModule(this)).inject(this);
        ContactsPresenter co = mContactsPresenter;
    }
}
