package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("IDSong")
    @Expose
    private String iDSong;
    @SerializedName("IDAlbum")
    @Expose
    private String iDAlbum;
    @SerializedName("IDCategory")
    @Expose
    private String iDCategory;
    @SerializedName("IDPlayList")
    @Expose
    private String iDPlayList;
    @SerializedName("NameSong")
    @Expose
    private String nameSong;
    @SerializedName("ImageSong")
    @Expose
    private String imageSong;
    @SerializedName("Singer")
    @Expose
    private String singer;
    @SerializedName("LinkSong")
    @Expose
    private String linkSong;
    @SerializedName("Likes")
    @Expose
    private String likes;

    public String getIDSong() {
        return iDSong;
    }

    public void setIDSong(String iDSong) {
        this.iDSong = iDSong;
    }

    public String getIDAlbum() {
        return iDAlbum;
    }

    public void setIDAlbum(String iDAlbum) {
        this.iDAlbum = iDAlbum;
    }

    public String getIDCategory() {
        return iDCategory;
    }

    public void setIDCategory(String iDCategory) {
        this.iDCategory = iDCategory;
    }

    public String getIDPlayList() {
        return iDPlayList;
    }

    public void setIDPlayList(String iDPlayList) {
        this.iDPlayList = iDPlayList;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

}