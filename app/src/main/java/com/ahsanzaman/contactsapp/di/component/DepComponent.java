package com.ahsanzaman.contactsapp.di.component;

import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.di.module.NetworkModule;
import com.ahsanzaman.contactsapp.module.contacts.view.ContactsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Accolite- on 7/21/2017.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface DepComponent {

    ContactsComponent plus(ContactsModule contactsModule);
}