package com.ahsanzaman.contactsapp.di.module;

import android.content.Context;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Accolite- on 7/24/2017.
 */

@Module
public class ContactDetailsModule {


    private final ContactDetailsActivity mContactDetailsActivity;
    private final Long mContactID;

    public ContactDetailsModule(ContactDetailsActivity contactDetailsActivity, Long contactID) {
        mContactDetailsActivity = contactDetailsActivity;
        mContactID = contactID;
    }

    @Provides
    Context providesContext() {
        return mContactDetailsActivity;
    }

    @Provides
    Long providesContact(){
        return mContactID;
    }

}
