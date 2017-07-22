package com.ahsanzaman.contactsapp.di.component;

import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.module.contacts.view.ContactsActivity;

import dagger.Subcomponent;

/**
 * Created by Accolite- on 7/22/2017.
 */
@Subcomponent(
        modules = ContactsModule.class
)
public interface ContactsComponent {

        void inject(ContactsActivity contactsActivity);

}
