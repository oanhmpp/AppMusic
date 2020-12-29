package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//model tạo từ jsonschema2pojo
public class Advertisement implements Serializable {

@SerializedName("IDAdver")
@Expose
private String iDAdver;
@SerializedName("ImageAdver")
@Expose
private String imageAdver;
@SerializedName("ContentAdver")
@Expose
private String contentAdver;
@SerializedName("IDSong")
@Expose
private String iDSong;
@SerializedName("NameSong")
@Expose
private String nameSong;
@SerializedName("ImageSong")
@Expose
private String imageSong;

public String getIDAdver() {
return iDAdver;
}

public void setIDAdver(String iDAdver) {
this.iDAdver = iDAdver;
}

public String getImageAdver() {
return imageAdver;
}

public void setImageAdver(String imageAdver) {
this.imageAdver = imageAdver;
}

public String getContentAdver() {
return contentAdver;
}

public void setContentAdver(String contentAdver) {
this.contentAdver = contentAdver;
}

public String getIDSong() {
return iDSong;
}

public void setIDSong(String iDSong) {
this.iDSong = iDSong;
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

}