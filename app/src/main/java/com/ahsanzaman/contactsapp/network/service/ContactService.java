package com.ahsanzaman.contactsapp.network.service;

import android.accounts.NetworkErrorException;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactService implements IContactsService {
    private final NetworkService networkService;

    public ContactService(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Disposable getContactsList(final RemoteServiceCallback callback, final int requestCode) {
        return networkService.getContactList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<List<ContactResponse>, List<Contact>>() {
                    @Override
                    public List<Contact> apply(List<ContactResponse> contactResponses) throws Exception {
                        List<Contact> contacts = new ArrayList<Contact>();
                        if(contactResponses!=null && contactResponses.size()>0) {
                            for (ContactResponse contactResponse : contactResponses) {
                                Contact contact = new Contact(contactResponse);
                                contacts.add(contact);
                            }
                        }
                        return contacts;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> contacts) throws Exception {
                        callback.onSuccess(contacts, requestCode);
                    }
                });
    }

    @Override
    public Disposable getContactDetail(final RemoteServiceCallback callback, long id, final int requestCode) {
        return networkService.getContactDetail(id+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse contactDetailResponse) throws Exception {
                        callback.onSuccess(contactDetailResponse, requestCode);
                    }
                });
    }

    @Override
    public Disposable editContactDetail(final RemoteServiceCallback callback, long id, final int requestCode, ContactDetailResponse contactDetailResponse) {
        return networkService.editContactDetail(id+"", contactDetailResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse contactDetailResponse) throws Exception {
                        callback.onSuccess(contactDetailResponse, requestCode);
                    }
                });
    }

    @Override
    public Disposable addContactDetail(final RemoteServiceCallback callback, final int requestCode, ContactDetailResponse contactDetail) {
        return networkService.addContact(contactDetail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse contactDetailResponse) throws Exception {
                        callback.onSuccess(contactDetailResponse, requestCode);
                    }
                });
    }
}
