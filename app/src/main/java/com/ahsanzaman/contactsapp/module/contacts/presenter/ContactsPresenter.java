package com.ahsanzaman.contactsapp.module.contacts.presenter;

import android.content.Context;

import com.ahsanzaman.contactsapp.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.module.contacts.view.ContactsActivity;
import com.ahsanzaman.contactsapp.module.contacts.view.ContactsView;
import com.ahsanzaman.contactsapp.network.service.NetworkService;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactsPresenter extends BasePresenter {


    private final NetworkService mNetworkService;
    private final ContactsView mContactsView;

    @Inject
    public ContactsPresenter(NetworkService networkService, Context contactsActivity) {
        mNetworkService = networkService;
        mContactsView = (ContactsView) contactsActivity;
    }
}
