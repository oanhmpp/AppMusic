package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayList {

@SerializedName("IDPlayList")
@Expose
private String iDPlayList;
@SerializedName("NamePlayList")
@Expose
private String namePlayList;
@SerializedName("Background")
@Expose
private String background;
@SerializedName("ImageIcon")
@Expose
private String imageIcon;

public String getIDPlayList() {
return iDPlayList;
}

public void setIDPlayList(String iDPlayList) {
this.iDPlayList = iDPlayList;
}

public String getNamePlayList() {
return namePlayList;
}

public void setNamePlayList(String namePlayList) {
this.namePlayList = namePlayList;
}

public String getBackground() {
return background;
}

public void setBackground(String background) {
this.background = background;
}

public String getImageIcon() {
return imageIcon;
}

public void setImageIcon(String imageIcon) {
this.imageIcon = imageIcon;
}

}