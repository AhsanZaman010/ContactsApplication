package com.ahsanzaman.contactsapp.network.service;

import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */

public interface IContactsService {
    Disposable getContactsList(RemoteServiceCallback callback, int requestCode);

    Disposable getContactDetail(RemoteServiceCallback callback, long id, int requestCode);

    Disposable editContactDetail(RemoteServiceCallback callback, long id, int requestCode, ContactDetail contactDetail);

    Disposable addContactDetail(RemoteServiceCallback callback, int requestCode, ContactDetail contactDetail);
}
