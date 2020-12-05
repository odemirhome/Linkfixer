
package com.entegman.radio.linkfixer.model.radionet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityTag {

    @SerializedName("systemName")
    @Expose
    private String systemName;
    @SerializedName("name")
    @Expose
    private String name;

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

}
