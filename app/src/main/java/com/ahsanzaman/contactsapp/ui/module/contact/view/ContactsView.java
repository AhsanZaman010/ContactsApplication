package com.ahsanzaman.contactsapp.ui.module.contact.view;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

import java.util.List;

/**
 * Created by Accolite- on 7/21/2017.
 */

public interface ContactsView extends BaseView {
    void updateContacts(List<Contact> contactList);

    void showAddContactButton();

    void hideAddContactButton();

    void showNoDataFound();

    void hideNoDataFound();

    void showContactDetails(Contact item);

    void openAddContactScreen();
}
