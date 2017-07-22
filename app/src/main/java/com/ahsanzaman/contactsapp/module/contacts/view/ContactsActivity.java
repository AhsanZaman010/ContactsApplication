package com.ahsanzaman.contactsapp.module.contacts.view;

import android.os.Bundle;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.module.contacts.adapter.ContactsAdapter;
import com.ahsanzaman.contactsapp.module.contacts.presenter.ContactsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ContactsActivity extends BaseActivity implements ContactsView {


    @Inject
    ContactsPresenter mContactsPresenter;
    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((ContactsApplication) getApplicationContext()).getDeps().plus(new ContactsModule(this)).inject(this);
        mContactsAdapter = new ContactsAdapter(this, new ArrayList<Contact>(), new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Contact item) {
                mContactsPresenter.onItemClick(item);
            }
        });
        mContactsPresenter.getContacts();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void updateContacts(List<Contact> contactList) {

    }
}
