package com.ahsanzaman.contactsapp.module.contacts.view;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.module.base.BaseView;

import java.util.List;

/**
 * Created by Accolite- on 7/21/2017.
 */

public interface ContactsView extends BaseView {
    void updateContacts(List<Contact> contactList);
}
