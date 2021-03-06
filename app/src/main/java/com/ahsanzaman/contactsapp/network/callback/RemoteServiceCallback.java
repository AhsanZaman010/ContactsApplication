package com.ahsanzaman.contactsapp.network.callback;

import android.accounts.NetworkErrorException;

/**
 * Created by Accolite- on 7/26/2017.
 */

public interface RemoteServiceCallback<T> {

    void onSuccess(T responseObject, int requestCode);

    void onError(NetworkErrorException networkError, int requestCode);

}
