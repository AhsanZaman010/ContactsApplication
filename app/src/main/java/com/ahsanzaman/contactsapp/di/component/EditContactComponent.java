package com.ahsanzaman.contactsapp.di.component;

import android.app.Activity;

import com.ahsanzaman.contactsapp.di.module.EditContactModule;

import dagger.Subcomponent;

/**
 * Created by Accolite- on 7/25/2017.
 */

@Subcomponent(
        modules = {EditContactModule.class}
)
public interface EditContactComponent {

    void inject(Activity activity);

}
