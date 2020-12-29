package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("IDCategory")
    @Expose
    private String IDCategory;
    @SerializedName("IDTheme")
    @Expose
    private String IDTheme;
    @SerializedName("NameCategory")
    @Expose
    private String NameCategory;
    @SerializedName("ImageCategory")
    @Expose
    private String ImageCategory;

    public String getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(String IDCategory) {
        this.IDCategory = IDCategory;
    }

    public String getIDTheme() {
        return IDTheme;
    }

    public void setIDTheme(String IDTheme) {
        this.IDTheme = IDTheme;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public void setNameCategory(String nameCategory) {
        NameCategory = nameCategory;
    }

    public String getImageCategory() {
        return ImageCategory;
    }

    public void setImageCategory(String imageCategory) {
        ImageCategory = imageCategory;
    }
}