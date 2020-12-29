package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("IDAlbum")
    @Expose
    private String iDAlbum;
    @SerializedName("NameAlbum")
    @Expose
    private String nameAlbum;
    @SerializedName("NameSingerAlbum")
    @Expose
    private String nameSingerAlbum;
    @SerializedName("ImageAlbum")
    @Expose
    private String imageAlbum;

    public String getIDAlbum() {
        return iDAlbum;
    }

    public void setIDAlbum(String iDAlbum) {
        this.iDAlbum = iDAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameSingerAlbum() {
        return nameSingerAlbum;
    }

    public void setNameSingerAlbum(String nameSingerAlbum) {
        this.nameSingerAlbum = nameSingerAlbum;
    }

    public String getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(String imageAlbum) {
        this.imageAlbum = imageAlbum;
    }

}
