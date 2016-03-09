package com.twittercrashlytics.network.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class User {

    private String login;

    @SerializedName("avatar_url")
    private String profilepicUrl;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProfilepicUrl() {
        return profilepicUrl;
    }

    public void setProfilepicUrl(String profilepicUrl) {
        this.profilepicUrl = profilepicUrl;
    }
}