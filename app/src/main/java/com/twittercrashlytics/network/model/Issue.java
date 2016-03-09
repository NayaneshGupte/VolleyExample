package com.twittercrashlytics.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model for issues
 */
public class Issue extends BaseModel {

    private String title;

    private String url;

    @SerializedName("comments_url")
    private String commentsUrl;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

}
