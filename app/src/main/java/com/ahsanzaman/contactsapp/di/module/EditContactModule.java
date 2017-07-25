package com.ahsanzaman.contactsapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;

import dagger.Provides;

/**
 * Created by Accolite- on 7/25/2017.
 */

public class EditContactModule {

    private final Activity mActivity;
    private final ContactDetailResponse mContactDetailResponse;

    public EditContactModule(Activity activity, ContactDetailResponse contactDetailResponse) {
        mActivity = activity;
        mContactDetailResponse = contactDetailResponse;
    }

    @Provides
    Context providesContext() {
        return mActivity;
    }

    @Provides
    ContactDetailResponse providesResponse() {
        return mContactDetailResponse;
    }

}
