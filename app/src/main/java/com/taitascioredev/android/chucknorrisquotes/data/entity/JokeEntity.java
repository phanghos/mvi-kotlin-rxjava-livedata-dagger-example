package com.taitascioredev.android.chucknorrisquotes.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rrtatasciore on 24/12/17.
 */

public class JokeEntity {

    @SerializedName("categories")
    private List<String> category;
    @SerializedName("icon_url")
    private String iconUrl;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("value")
    private String value;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
