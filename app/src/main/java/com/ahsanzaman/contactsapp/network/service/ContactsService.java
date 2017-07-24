package com.ahsanzaman.contactsapp.network.service;

import android.accounts.NetworkErrorException;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;

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

public class ContactsService {
    private final NetworkService networkService;

    public ContactsService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Disposable getCityList(final GetContactsCallback callback) {
        int a =0;
        a++;
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
                        callback.onError(new NetworkErrorException(throwable));
                    }
                })
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> contacts) throws Exception {
                        callback.onSuccess(contacts);
                    }
                });
    }

    public interface GetContactsCallback {
        void onSuccess(List<Contact> contactList);

        void onError(NetworkErrorException networkError);
    }
}
