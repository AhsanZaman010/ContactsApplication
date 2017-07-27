package com.ahsanzaman.contactsapp.data.repository;

import android.app.Application;

import com.ahsanzaman.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */

public class RealmRepository implements ILocalRepository {

    private static final String ID = "id";
    private final Application mApplication;

    public RealmRepository(Application application){
        this.mApplication = application;
    }

    @Override
    public void addContact(Contact contact) {
       /* Realm realm = Realm.getInstance(mApplication);
        realm.beginTransaction();
        Contact u = realm.createObject(Contact.class);
        u.setId(UUID.randomUUID().toString());
        u.setName(contact.getName());
        realm.commitTransaction();
        if ( != null)
            .onSuccess();*/
    }


    @Override
    public Contact getContactById(Long id) {
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
    public void setAllContacts(List<Contact> contacts) {
        clearContacts();
        Realm realm = Realm.getInstance(mApplication);
        realm.beginTransaction();
        realm.copyToRealm(contacts);
        realm.commitTransaction();
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
