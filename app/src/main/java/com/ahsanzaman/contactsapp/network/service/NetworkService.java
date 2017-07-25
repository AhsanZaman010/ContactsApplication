package com.ahsanzaman.contactsapp.network.service;

import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Accolite- on 7/21/2017.
 */

public interface NetworkService {

    @GET("/contacts.json")
    Observable<List<ContactResponse>> getContactList();

    @GET("/contacts/{id}.json")
    Observable<ContactDetailResponse> getContactDetail(@Path("id") String id);

    @PUT("/contacts/{id}.json")
    Observable<ContactDetailResponse> editContactDetail(@Path("id") String id, @Body ContactDetailResponse contactDetailResponse);

    @POST("/contacts.json")
    Observable<ContactDetailResponse> addContact(@Body ContactDetailResponse contactDetailResponse);

}
