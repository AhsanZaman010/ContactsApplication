package com.ahsanzaman.contactsapp.network.service;

import android.accounts.NetworkErrorException;

import com.ahsanzaman.contactsapp.model.Contact;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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

        return networkService.getContactList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<List<Contact>, List<Contact>>() {
                    @Override
                    public List<Contact> apply(List<Contact> contacts) throws Exception {
                        return contacts;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<Contact>>>() {
                    @Override
                    public ObservableSource<? extends List<Contact>> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable));
                    }
                })
                .doOnNext(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> contacts) throws Exception {
                        callback.onSuccess(contacts);
                    }
                })
                .subscribe();
    }

    public interface GetContactsCallback {
        void onSuccess(List<Contact> contactList);

        void onError(NetworkErrorException networkError);
    }
}
