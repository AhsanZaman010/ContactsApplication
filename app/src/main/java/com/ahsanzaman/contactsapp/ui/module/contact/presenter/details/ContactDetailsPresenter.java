package com.ahsanzaman.contactsapp.ui.module.contact.presenter.details;

import android.content.Context;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.network.service.ContactsService;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsView;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/23/2017.
 */

public class ContactDetailsPresenter extends BasePresenter {

    private final ContactsService mContactsService;
    private final ContactDetailsView mContactDetailsView;
    private final Contact mContact;

    @Inject
    public ContactDetailsPresenter(ContactsService contactsService, Context context, Long contactID, IContactsRepository contactsRepository) {
        mContactsService = contactsService;
        mContactDetailsView = (ContactDetailsView) context;
        mContact = contactsRepository.getContactById(contactID);
    }
}
