package com.ahsanzaman.contactsapp.module.base;

import android.app.Application;
import android.os.Bundle;

import com.ahsanzaman.contactsapp.di.component.DaggerDepComponent;
import com.ahsanzaman.contactsapp.di.component.DepComponent;
import com.ahsanzaman.contactsapp.di.module.NetworkModule;

import java.io.File;

/**
 * Created by Accolite- on 7/21/2017.
 */

public class ContactsApplication extends Application {

    private DepComponent deps;



    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDepComponent.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public DepComponent getDeps() {
        return deps;
    }

}
