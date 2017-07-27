
package com.ahsanzaman.contactsapp.model;

import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contact extends RealmObject {

    @PrimaryKey
    private long id;
    private String firstName;
    private String lastName;
    private String profilePic;
    private boolean favorite;
    private String url;

    public Contact(ContactResponse contactResponse) {
        setId(contactResponse.getId());
        setFirstName(contactResponse.getFirstName());
        setLastName(contactResponse.getLastName());
        setProfilePic(contactResponse.getProfilePic());
        setFavorite(contactResponse.isFavorite());
        setUrl(contactResponse.getUrl());
    }

    public Contact(long id, String firstName, String lastName, boolean favorite) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favorite = favorite;
    }

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
