package com.taitascioredev.android.chucknorrisquotes.model;

import java.util.List;

/**
 * Created by rrtatasciore on 24/12/17.
 */

public class Joke {

    private List<String> category;
    private String iconUrl;
    private String id;
    private String url;
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
