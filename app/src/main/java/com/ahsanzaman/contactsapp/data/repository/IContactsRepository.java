package com.ahsanzaman.contactsapp.data.repository;

import android.support.annotation.NonNull;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;

/**
 * Created by Accolite- on 7/22/2017.
 */

public interface IContactsRepository {

    Disposable getContacts(@NonNull final RemoteServiceCallback callback, int requestCode);

    Disposable getContactDetail(RemoteServiceCallback callback, long id, int requestCode);

    Disposable editContactDetail(RemoteServiceCallback callback, long id, int requestCode, ContactDetailResponse contactDetailResponse);

    Disposable addContactDetail(RemoteServiceCallback callback, int requestCode, ContactDetailResponse contactDetail);

    ILocalRepository getLocalRepository();

}
