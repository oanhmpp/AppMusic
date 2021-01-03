package com.example.appmusic.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Theme implements Serializable {

    @SerializedName("IDTheme")
    @Expose
    private String iDTheme;
    @SerializedName("NameTheme")
    @Expose
    private String nameTheme;
    @SerializedName("ImageTheme")
    @Expose
    private String imageTheme;

    public String getIDTheme() {
        return iDTheme;
    }

    public void setIDTheme(String iDTheme) {
        this.iDTheme = iDTheme;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public String getImageTheme() {
        return imageTheme;
    }

    public void setImageTheme(String imageTheme) {
        this.imageTheme = imageTheme;
    }

}