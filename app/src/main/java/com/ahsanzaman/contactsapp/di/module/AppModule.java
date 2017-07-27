package com.ahsanzaman.contactsapp.di.module;

import android.app.Application;

import com.ahsanzaman.contactsapp.data.repository.ContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Accolite- on 7/22/2017.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }


}
