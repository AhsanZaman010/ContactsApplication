package com.ahsanzaman.contactsapp.ui.module.contact.presenter;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.network.service.ContactsService;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsView;
import com.ahsanzaman.contactsapp.utils.ContactUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactsPresenter extends BasePresenter implements ContactsService.GetContactsCallback {

    private final String TAG = getClass().getSimpleName();

    private final ContactsService mContactsService;
    private final ContactsView mContactsView;
    private final IContactsRepository mContactsRepository;

    @Inject
    public ContactsPresenter(ContactsService contactsService, Context contactsActivity, IContactsRepository contactsRepository) {
        mContactsService = contactsService;
        mContactsView = (ContactsView) contactsActivity;
        mContactsRepository = contactsRepository;
    }

    public void getContacts(){
        mContactsView.showLoading();
        List<Contact> contacts = mContactsRepository.getAllContacts();
        if(contacts == null || contacts.size() == 0){
            getContactsFromRemote();
        } else {
            ContactUtils.sortContacts(contacts);
            ContactsPresenter.this.showContacts(contacts);
        }
    }

    public void getContactsFromRemote() {
        Disposable disposable = mContactsService.getCityList(ContactsPresenter.this);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onSuccess(final List<Contact> contactList) {
        mContactsRepository.setAllContacts(contactList);
        showContacts(contactList);
    }

    public void showContacts(List<Contact> contactList) {
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
