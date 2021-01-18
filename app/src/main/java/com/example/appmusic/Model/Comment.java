package com.example.appmusic.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("IDCmt")
    @Expose
    private String iDCmt;
    @SerializedName("NameUser")
    @Expose
    private String nameUser;
    @SerializedName("ContentComment")
    @Expose
    private String contentComment;
    @SerializedName("IDSong")
    @Expose
    private String iDSong;

    public String getIDCmt() {
        return iDCmt;
    }

    public void setIDCmt(String iDCmt) {
        this.iDCmt = iDCmt;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getIDSong() {
        return iDSong;
    }

    public void setIDSong(String iDSong) {
        this.iDSong = iDSong;
    }

}