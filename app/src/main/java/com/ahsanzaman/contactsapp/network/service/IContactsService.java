package com.ahsanzaman.contactsapp.network.service;

import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */

public interface IContactsService {
    Disposable getContactsList(RemoteServiceCallback callback, int requestCode);

    Disposable getContactDetail(RemoteServiceCallback callback, long id, int requestCode);

    Disposable editContactDetail(RemoteServiceCallback callback, long id, int requestCode, ContactDetailResponse contactDetailResponse);

    Disposable addContactDetail(RemoteServiceCallback callback, int requestCode, ContactDetailResponse contactDetail);
}
