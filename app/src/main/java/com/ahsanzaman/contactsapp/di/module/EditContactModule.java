package com.ahsanzaman.contactsapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.contact.view.edit.EditContactView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Accolite- on 7/25/2017.
 */

@Module
public class EditContactModule {

    private final Activity mActivity;
    private final ContactDetail mContactDetail;

    public EditContactModule(Activity activity, ContactDetail contactDetail) {
        mActivity = activity;
        mContactDetail = contactDetail;
    }

    @Provides
    EditContactView providesView() {
        return (EditContactView) mActivity;
    }

    @Provides
    ContactDetail providesResponse() {
        return mContactDetail;
    }

}
