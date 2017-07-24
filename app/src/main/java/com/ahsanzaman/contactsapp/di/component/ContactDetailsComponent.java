package com.ahsanzaman.contactsapp.di.component;

import com.ahsanzaman.contactsapp.di.module.ContactDetailsModule;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsActivity;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;

import dagger.Subcomponent;

/**
 * Created by Ahsan Zaman on 24-07-2017.
 */

@Subcomponent(
        modules = {ContactDetailsModule.class}
)
public interface ContactDetailsComponent {

    void inject(ContactDetailsActivity contactDetailsActivity);

}
