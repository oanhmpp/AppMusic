package com.example.appmusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song implements Parcelable {

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

    public Song(String iDSong, String nameSong,String singer, String imageSong ) {
        this.iDSong = iDSong;
        this.nameSong = nameSong;
        this.singer = singer;
        this.imageSong = imageSong;
    }

    public Song(String iDSong, String nameSong, String singer, String imageSong, String iDAlbum, String iDCategory, String iDPlayList, String linkSong) {
        this.iDSong = iDSong;
        this.nameSong = nameSong;
        this.singer = singer;
        this.imageSong = imageSong;
        this.iDAlbum=iDAlbum;
        this.iDCategory=iDCategory;
        this.iDPlayList=iDPlayList;
        this.linkSong=linkSong;
    }

    protected Song(Parcel in) {
        iDSong = in.readString();
        iDAlbum = in.readString();
        iDCategory = in.readString();
        iDPlayList = in.readString();
        nameSong = in.readString();
        imageSong = in.readString();
        singer = in.readString();
        linkSong = in.readString();
        likes = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDSong);
        dest.writeString(iDAlbum);
        dest.writeString(iDCategory);
        dest.writeString(iDPlayList);
        dest.writeString(nameSong);
        dest.writeString(imageSong);
        dest.writeString(singer);
        dest.writeString(linkSong);
        dest.writeString(likes);
    }
}