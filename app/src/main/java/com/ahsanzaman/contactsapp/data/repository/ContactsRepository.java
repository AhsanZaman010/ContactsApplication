package com.ahsanzaman.contactsapp.data.repository;

import android.app.Application;

import com.ahsanzaman.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Accolite- on 7/22/2017.
 */

public class ContactsRepository implements IContactsRepository{

    private static final String ID = "id";
    private static ContactsRepository sInstance;
    private final Application mApplication;

    private ContactsRepository(Application application){
        this.mApplication = application;
    }

    public static synchronized ContactsRepository getInstance(Application application){
        if(sInstance == null){
            sInstance = new ContactsRepository(application);
        }
        return sInstance;
    }

    @Override
    public void addContact(Contact contact) {
       /* Realm realm = Realm.getInstance(mApplication);
        realm.beginTransaction();
        Contact u = realm.createObject(Contact.class);
        u.setId(UUID.randomUUID().toString());
        u.setName(contact.getName());
        realm.commitTransaction();
        if (callback != null)
            callback.onSuccess();*/
    }


    @Override
    public Contact getContactById(String id) {
        Realm realm = Realm.getInstance(mApplication);
        Contact result = realm.where(Contact.class).equalTo(ID, id).findFirst();
        return result;
    }

    @Override
    public List<Contact> getAllContacts() {
        Realm realm = Realm.getInstance(mApplication);
        RealmQuery query = realm.where(Contact.class);
        List<Contact> contacts = new ArrayList<>(query.findAll());
        return contacts;
    }

    @Override
    public void clearContacts() {
        Realm realm = Realm.getInstance(mApplication);
        realm.beginTransaction();
        RealmQuery query = realm.where(Contact.class);
        RealmResults results = query.findAll();
        results.clear();
        realm.commitTransaction();

    }


    @Override
    public List<Contact> setAllContacts(List<Contact> contacts) {
        clearContacts();
        Realm realm = Realm.getInstance(mApplication);
        realm.beginTransaction();
        realm.copyToRealm(contacts);
        realm.commitTransaction();
        return contacts;
    }

    @Override
    public void updateContact(Contact contact) {
        clearContacts();
        Realm realm = Realm.getInstance(mApplication);
        realm.beginTransaction();
        realm.copyToRealm(contact);
        realm.commitTransaction();
    }

}