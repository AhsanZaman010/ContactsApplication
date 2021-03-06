package com.ahsanzaman.contactsapp.di.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;

import com.ahsanzaman.contactsapp.BuildConfig;
import com.ahsanzaman.contactsapp.data.repository.ContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.ILocalRepository;
import com.ahsanzaman.contactsapp.data.repository.RealmRepository;
import com.ahsanzaman.contactsapp.network.service.ContactService;
import com.ahsanzaman.contactsapp.network.service.IContactsService;
import com.ahsanzaman.contactsapp.network.service.NetworkService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Accolite- on 7/21/2017.
 */

@Module
public class NetworkModule {
    private final Application mApplication;
    private File cacheFile;

    public NetworkModule(File cacheFile, Application application) {
        this.cacheFile = cacheFile;
        mApplication = application;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        @SuppressLint("DefaultLocale") Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .cache(cache)

                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public IContactsService providesService(
            NetworkService networkService) {
        return new ContactService(networkService);
    }

    @Provides
    @Singleton
    IContactsRepository providesRepository(IContactsService contactsService, ILocalRepository localRepository) {
        return new ContactsRepository(contactsService, localRepository);
    }

    @Provides
    @Singleton
    ILocalRepository providesLocalRepository(Application application) {
        return new RealmRepository(application);
    }

}