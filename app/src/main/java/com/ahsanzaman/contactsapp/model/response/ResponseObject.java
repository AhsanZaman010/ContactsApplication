package com.ahsanzaman.contactsapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Accolite- on 7/26/2017.
 */

public class ResponseObject {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("status")
    @Expose
    private String status;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
