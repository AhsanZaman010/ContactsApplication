package com.ahsanzaman.contactsapp.di.component;

import com.ahsanzaman.contactsapp.di.module.ContactDetailsModule;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsActivity;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;

import dagger.Subcomponent;

/**
 * Created by Accolite- on 7/22/2017.
 */
@Subcomponent(
        modules = {ContactsModule.class, ContactDetailsModule.class}
)
public interface ContactsComponent {

        void inject(ContactsActivity contactsActivity);

        void inject(ContactDetailsActivity contactDetailsActivity);

}
