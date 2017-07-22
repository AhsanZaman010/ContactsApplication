package com.ahsanzaman.contactsapp.di.module;

import android.content.Context;

import com.ahsanzaman.contactsapp.module.contacts.view.ContactsActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Accolite- on 7/22/2017.
 */

@Module
public class ContactsModule {


    private final ContactsActivity mContactsActivity;

    public ContactsModule(ContactsActivity contactsActivity) {
        mContactsActivity = contactsActivity;
    }

    @Provides
    Context providesContext() {
        return mContactsActivity;
    }

}
