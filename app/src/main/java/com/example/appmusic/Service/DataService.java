package com.example.appmusic.Service;

import com.example.appmusic.Model.Advertisement;
//import com.example.appmusic.Model.PlayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//phuong thuc tuong tac voi server, server tra ve
public interface DataService {
    @GET("Server/songBanner.php")
        Call<List<Advertisement>> getDataBanner();
//    @GET("Server/playlistforcurrentday.php")
//    Call<List<PlayList>> getDataBanner();
}
