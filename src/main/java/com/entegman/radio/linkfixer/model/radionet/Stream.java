
package com.entegman.radio.linkfixer.model.radionet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stream {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("contentFormat")
    @Expose
    private String contentFormat;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentFormat() {
        return contentFormat;
    }

    public void setContentFormat(String contentFormat) {
        this.contentFormat = contentFormat;
    }

}
