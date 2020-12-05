
package com.entegman.radio.linkfixer.model.radionet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicTag {

    @SerializedName("systemName")
    @Expose
    private String systemName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
