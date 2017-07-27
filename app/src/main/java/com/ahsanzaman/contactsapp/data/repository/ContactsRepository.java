package com.ahsanzaman.contactsapp.data.repository;

import android.support.annotation.NonNull;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.network.service.IContactsService;
import com.ahsanzaman.contactsapp.utils.CollectionUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Accolite- on 7/22/2017.
 */

public class ContactsRepository implements IContactsRepository{

    private static final String ID = "id";
    private final IContactsService mContactsService;
    private final ILocalRepository mLocalRepository;
    private List<Contact> mCachedContacts;

    public ContactsRepository(IContactsService contactsService, ILocalRepository localRepository){
        mContactsService = contactsService;
        mLocalRepository = localRepository;
    }

    @Override
    public Disposable getContacts(@NonNull final RemoteServiceCallback callback, int requestCode) {
        Disposable disposable = null;
        if(mCachedContacts ==null || mCachedContacts.size() == 0) {
            List<Contact> contacts = mLocalRepository.getAllContacts();
            if(CollectionUtils.isEmpty(contacts)){
                disposable = mContactsService.getContactsList(callback, requestCode);
            } else {
                callback.onSuccess(contacts, requestCode);
            }
        } else {
            callback.onSuccess(mCachedContacts, requestCode);
        }
        return disposable;
    }

    @Override
    public Disposable getContactDetail(RemoteServiceCallback callback, long id, int requestCode) {
        return mContactsService.getContactDetail(callback, id, requestCode);
    }

    @Override
    public Disposable editContactDetail(RemoteServiceCallback callback, long id, int requestCode, ContactDetailResponse contactDetailResponse) {
        return mContactsService.editContactDetail(callback, id, requestCode, contactDetailResponse);
    }

    @Override
    public Disposable addContactDetail(RemoteServiceCallback callback, int requestCode, ContactDetailResponse contactDetail) {
        return mContactsService.addContactDetail(callback, requestCode, contactDetail);
    }

    @Override
    public ILocalRepository getLocalRepository() {
        return mLocalRepository;
    }

}