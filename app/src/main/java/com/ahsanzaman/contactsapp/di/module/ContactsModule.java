package com.ahsanzaman.contactsapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Accolite- on 7/22/2017.
 */

@Module
public class ContactsModule {


    private final Activity mActivity;

    public ContactsModule(Activity contactsActivity) {
        mActivity = contactsActivity;
    }

    @Provides
    Context providesContext() {
        return mActivity;
    }

}
