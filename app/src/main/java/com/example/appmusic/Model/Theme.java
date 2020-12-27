package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Theme implements Serializable {

    @SerializedName("IDTheme")
    @Expose
    private String IDTheme;
    @SerializedName("NameTheme")
    @Expose
    private String NameTheme;
    @SerializedName("ImageTheme")
    @Expose
    private String ImageTheme;

    public String getIDTheme() {
        return IDTheme;
    }

    public void setIDTheme(String IDTheme) {
        this.IDTheme = IDTheme;
    }

    public String getNameTheme() {
        return NameTheme;
    }

    public void setNameTheme(String nameTheme) {
        NameTheme = nameTheme;
    }

    public String getImageTheme() {
        return ImageTheme;
    }

    public void setImageTheme(String imageTheme) {
        ImageTheme = imageTheme;
    }
}