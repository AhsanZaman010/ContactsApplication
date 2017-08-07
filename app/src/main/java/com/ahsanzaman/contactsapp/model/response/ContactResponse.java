package com.ahsanzaman.contactsapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Accolite- on 7/22/2017.
 */

public class ContactResponse extends ResponseObject {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("favorite")
    @Expose
    private boolean favorite;
    @SerializedName("url")
    @Expose
    private String url;

    public ContactResponse(long id, String firstName, String lastName, String profilePic, boolean favorite, String url) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePic = profilePic;
        this.favorite = favorite;
        this.url = url;
    }

    public ContactResponse() {
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
