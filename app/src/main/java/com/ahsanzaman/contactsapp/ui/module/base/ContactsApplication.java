package com.ahsanzaman.contactsapp.ui.module.base;

import android.app.Application;

import com.ahsanzaman.contactsapp.di.component.DaggerDepComponent;
import com.ahsanzaman.contactsapp.di.component.DepComponent;
import com.ahsanzaman.contactsapp.di.module.AppModule;
import com.ahsanzaman.contactsapp.di.module.NetworkModule;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactsApplication extends Application {

    private DepComponent deps;



    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        deps = DaggerDepComponent.builder()
                .networkModule(new NetworkModule(cacheFile, this))
                .appModule(new AppModule(this))
                .build();

    }

    public DepComponent getDeps() {
        return deps;
    }

}
