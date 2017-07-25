package com.ahsanzaman.contactsapp.network.callback;

import android.accounts.NetworkErrorException;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ResponeObject;

import java.util.List;

/**
 * Created by Accolite- on 7/26/2017.
 */

public interface RemoteServiceCallback<T> {

    void onSuccess(T responseObject, int requestCode);

    void onError(NetworkErrorException networkError, int requestCode);

}
