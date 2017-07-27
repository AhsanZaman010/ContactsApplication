package com.ahsanzaman.contactsapp.network.service;

import android.accounts.NetworkErrorException;
import android.text.TextUtils;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.ahsanzaman.contactsapp.model.response.ResponseObject;
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
                .map(new Function<ContactDetail, ContactDetail>() {
                    @Override
                    public ContactDetail apply(ContactDetail contactDetail) throws Exception {
                        if(!validateResponse(contactDetail)){
                            throw new NetworkErrorException(null!= contactDetail ? contactDetail.getError():"");
                        }
                        return contactDetail;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<ContactDetail>() {
                    @Override
                    public void accept(ContactDetail contactDetail) throws Exception {
                        callback.onSuccess(contactDetail, requestCode);
                    }
                });
    }

    @Override
    public Disposable editContactDetail(final RemoteServiceCallback callback, long id, final int requestCode, ContactDetail contactDetail) {
        return networkService.editContactDetail(id+"", contactDetail)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContactDetail, ContactDetail>() {
                    @Override
                    public ContactDetail apply(ContactDetail contactDetail) throws Exception {
                        if(!validateResponse(contactDetail)){
                            throw new NetworkErrorException(null!= contactDetail ? contactDetail.getError():"");
                        }
                        return contactDetail;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<ContactDetail>() {
                    @Override
                    public void accept(ContactDetail contactDetail) throws Exception {
                        callback.onSuccess(contactDetail, requestCode);
                    }
                });
    }

    @Override
    public Disposable addContactDetail(final RemoteServiceCallback callback, final int requestCode, ContactDetail contactDetail) {
        return networkService.addContact(contactDetail)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContactDetail, ContactDetail>() {
                    @Override
                    public ContactDetail apply(ContactDetail contactDetail) throws Exception {
                        if(!validateResponse(contactDetail)){
                            throw new NetworkErrorException(null!= contactDetail ? contactDetail.getError():"");
                        }
                        return contactDetail;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<ContactDetail>() {
                    @Override
                    public void accept(ContactDetail contactDetail) throws Exception {
                        callback.onSuccess(contactDetail, requestCode);
                    }
                });
    }

    private boolean validateResponse(ResponseObject responseObject) {
        if(null != responseObject && TextUtils.isEmpty(responseObject.getError())){
            return true;
        }
        return false;
    }
}
