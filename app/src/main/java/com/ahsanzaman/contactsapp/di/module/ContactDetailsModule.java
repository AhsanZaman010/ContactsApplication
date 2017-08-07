package com.ahsanzaman.contactsapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Accolite- on 7/24/2017.
 */

@Module
public class ContactDetailsModule {


    private final Activity mActivity;
    private final Long mContactID;

    public ContactDetailsModule(Activity activity, Long contactID) {
        mActivity = activity;
        mContactID = contactID;
    }

    @Provides
    ContactDetailsView providesContactDetailsView() {
        return (ContactDetailsView) mActivity;
    }

    @Provides
    Long providesContact(){
        return mContactID;
    }

}
