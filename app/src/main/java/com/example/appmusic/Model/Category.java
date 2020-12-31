package com.example.appmusic.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("IDCategory")
    @Expose
    private String iDCategory;
    @SerializedName("IDTheme")
    @Expose
    private String iDTheme;
    @SerializedName("NameCategory")
    @Expose
    private String nameCategory;
    @SerializedName("ImageCategory")
    @Expose
    private String imageCategory;

    public String getIDCategory() {
        return iDCategory;
    }

    public void setIDCategory(String iDCategory) {
        this.iDCategory = iDCategory;
    }

    public String getIDTheme() {
        return iDTheme;
    }

    public void setIDTheme(String iDTheme) {
        this.iDTheme = iDTheme;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

}