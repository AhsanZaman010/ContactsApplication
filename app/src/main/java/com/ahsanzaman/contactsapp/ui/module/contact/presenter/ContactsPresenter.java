package com.ahsanzaman.contactsapp.ui.module.contact.presenter;

import android.content.Context;
import android.util.Log;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.network.service.IContactsService;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsView;
import com.ahsanzaman.contactsapp.utils.CollectionUtils;
import com.ahsanzaman.contactsapp.utils.ContactUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactsPresenter extends BasePresenter {

    public static final int CONTACTS_REQUEST_CODE = 101;
    private final String TAG = getClass().getSimpleName();

    private final ContactsView mContactsView;
    private final IContactsRepository mContactsRepository;

    @Inject
    public ContactsPresenter(ContactsView contactsView, IContactsRepository contactsRepository) {
        super(contactsView);
        mContactsView = contactsView;
        mContactsRepository = contactsRepository;
    }

    public void loadContacts(boolean forceUpdate){
        mContactsView.hideNoDataFound();
        mContactsView.showLoading();
        mContactsView.hideNoDataFound();
        if(forceUpdate){
            mContactsRepository.getLocalRepository().clearContacts();
        }
        Disposable disposable = mContactsRepository.getContacts(this, CONTACTS_REQUEST_CODE);
        if(disposable!=null){
            mCompositeDisposable.add(disposable);
        }
    }

    public void showContacts(List<Contact> contactList) {
        Log.d(TAG, "Contacts received");
        mContactsView.hideLoading();
        mContactsView.updateContacts(contactList);
    }

    public void onItemClick(Contact item) {
        mContactsView.showContactDetails(item);
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        super.onSuccess(responseObject, requestCode);
        mContactsView.showAddContactButton();
        List<Contact> contactList = (List<Contact>) responseObject;
        if(!CollectionUtils.isEmpty(contactList)) {
            mContactsRepository.getLocalRepository().setAllContacts(contactList);
            showContacts(contactList);
        } else {
            mContactsView.hideLoading();
            mContactsView.showNoDataFound();
        }
    }

    public void addContact() {
        mContactsView.openAddContactScreen();
    }
}
