package com.ahsanzaman.contactsapp.di.component;

import com.ahsanzaman.contactsapp.di.module.AppModule;
import com.ahsanzaman.contactsapp.di.module.ContactDetailsModule;
import com.ahsanzaman.contactsapp.di.module.ContactsModule;
import com.ahsanzaman.contactsapp.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Accolite- on 7/21/2017.
 */

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface DepComponent {

    ContactsComponent plus(ContactsModule contactsModule);

    ContactsComponent plus(ContactDetailsModule contactDetailsModule);
}