package com.ahsanzaman.contactsapp.data.repository;

import com.ahsanzaman.contactsapp.model.Contact;

import java.util.List;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */

public interface ILocalRepository {

    void addContact(Contact contact);

    Contact getContactById(Long id);

    List<Contact> getAllContacts();

    void clearContacts();

    List<Contact> setAllContacts(List<Contact> contacts);

    void updateContact(Contact contact);


}
