package com.ahsanzaman.contactsapp.data.repository;

import com.ahsanzaman.contactsapp.model.Contact;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Accolite- on 7/22/2017.
 */

public interface IContactsRepository {
    void addContact(Contact contact);

    Contact getContactById(Long id);

    List<Contact> getAllContacts();

    void clearContacts();

    List<Contact> setAllContacts(List<Contact> contacts);

    void updateContact(Contact contact);
}
