package com.example.appmusic.Service;

import com.example.appmusic.Model.Advertisement;
import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.Category_Theme;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.Model.Song;
//import com.example.appmusic.Model.PlayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//phuong thuc tuong tac voi server, server tra ve
public interface DataService {
    @GET("Server/songBanner.php")
        Call<List<Advertisement>> getDataBanner();

    @GET("Server/playlistforcurrentday.php")
    Call<List<PlayList>> getPlayListCurrentDay();

    // lấy dữ liêu chủ đề và thể loại trên CSDL
    @GET("Server/themeandcategoryinday.php")
    Call<Category_Theme> getCategoryMusic();

    @GET("Server/songlove.php")
    Call<List<Song>> getSongHot();
    // Album
    @GET("Server/albumhot.php")
    Call<List<Album>> getAlbumHot();
}
