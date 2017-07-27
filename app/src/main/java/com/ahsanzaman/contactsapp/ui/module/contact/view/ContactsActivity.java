package com.ahsanzaman.contactsapp.ui.module.contact.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.ui.module.contact.adapter.ContactsAdapter;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends BaseActivity implements ContactsView {

    @BindView(R.id.contacts_list)
    RecyclerView mRecyclerView;

    @Inject
    ContactsPresenter mContactsPresenter;
    private ContactsAdapter mContactsAdapter;
    private ProgressDialog mProgressDialog;

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
                Intent intent = new Intent(ContactsActivity.this, ContactDetailsActivity.class);
                intent.putExtra(ContactDetailsActivity.CONTACT_ID, item.getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mContactsAdapter);
        mContactsPresenter.loadContacts(false);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mContactsPresenter;
    }

    @Override
    protected void retry() {
        mContactsPresenter.loadContacts(true);
    }

    @Override
    protected void onCancelErrorDialog(DialogInterface dialog) {
        dialog.dismiss();
        retry();
    }

    @Override
    public void updateContacts(List<Contact> contactList) {
        mContactsAdapter.updateItems(contactList)
                .notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
