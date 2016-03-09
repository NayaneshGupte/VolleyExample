package com.twittercrashlytics.network.model;

import com.google.gson.annotations.SerializedName;

/**
 */
public abstract class BaseModel {

    @SerializedName("updated_at")
    protected String updatedAt;

    protected String body;

    protected User user;

    protected String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
