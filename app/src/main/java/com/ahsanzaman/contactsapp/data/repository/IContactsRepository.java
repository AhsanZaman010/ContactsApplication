package com.ahsanzaman.contactsapp.data.repository;

import android.support.annotation.NonNull;

import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.Disposable;

/**
 * Created by Accolite- on 7/22/2017.
 */

public interface IContactsRepository {

    Disposable getContacts(@NonNull final RemoteServiceCallback callback, int requestCode);

    Disposable getContactDetail(RemoteServiceCallback callback, long id, int requestCode);

    Disposable editContactDetail(RemoteServiceCallback callback, long id, int requestCode, ContactDetail contactDetail);

    Disposable addContactDetail(RemoteServiceCallback callback, int requestCode, ContactDetail contactDetail);

    ILocalRepository getLocalRepository();

}
