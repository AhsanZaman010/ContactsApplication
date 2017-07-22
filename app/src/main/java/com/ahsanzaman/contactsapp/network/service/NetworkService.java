package com.ahsanzaman.contactsapp.network.service;

import com.ahsanzaman.contactsapp.model.Contact;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Accolite- on 7/21/2017.
 */

public interface NetworkService {

    @GET("v1/city")
    Observable<List<Contact>> getContactList();

}
