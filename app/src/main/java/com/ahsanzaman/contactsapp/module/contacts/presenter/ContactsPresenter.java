package com.ahsanzaman.contactsapp.module.contacts.presenter;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.module.contacts.view.ContactsActivity;
import com.ahsanzaman.contactsapp.module.contacts.view.ContactsView;
import com.ahsanzaman.contactsapp.network.service.ContactsService;
import com.ahsanzaman.contactsapp.network.service.NetworkService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactsPresenter extends BasePresenter implements ContactsService.GetContactsCallback {

    private final String TAG = getClass().getSimpleName();

    private final ContactsService mContactsService;
    private final ContactsView mContactsView;

    @Inject
    public ContactsPresenter(ContactsService contactsService, Context contactsActivity) {
        mContactsService = contactsService;
        mContactsView = (ContactsView) contactsActivity;
    }

    public void getContacts(){
        mContactsView.showLoading();
        mContactsService.getCityList(this);
    }

    @Override
    public void onSuccess(List<Contact> contactList) {
        Log.d(TAG, "Contacts received");
        mContactsView.hideLoading();
        mContactsView.updateContacts(contactList);
    }

    @Override
    public void onError(NetworkErrorException networkError) {
        Log.e(TAG, "Error loading contacts");
        mContactsView.hideLoading();
    }

    public void onItemClick(Contact item) {

    }
}
