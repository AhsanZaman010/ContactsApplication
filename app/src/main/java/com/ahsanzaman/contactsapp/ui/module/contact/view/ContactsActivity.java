package com.ahsanzaman.contactsapp.ui.module.contact.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.ui.module.contact.adapter.ContactsAdapter;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.add.AddContactActivity;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsActivity extends BaseActivity implements ContactsView {

    @BindView(R.id.contacts_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.add_contact_fab)
    FloatingActionButton mAddContactFAB;
    @BindView(R.id.no_contacts_tv)
    View mNoDataFoundView;

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
    public void showAddContactButton(){
        mAddContactFAB.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAddContactButton(){
        mAddContactFAB.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataFound() {
        mNoDataFoundView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataFound() {
        mNoDataFoundView.setVisibility(View.GONE);
    }

    @Override
    public void showContactDetails(Contact item){
        if(null != item) {
            Intent intent = new Intent(ContactsActivity.this, ContactDetailsActivity.class);
            intent.putExtra(ContactDetailsActivity.CONTACT_ID, item.getId());
            startActivity(intent);
        }
    }

    @Override
    public void openAddContactScreen() {
        Intent intent = new Intent(ContactsActivity.this, AddContactActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.add_contact_fab)
    void onAddClicked(){
        mContactsPresenter.addContact();
    }


}
