package com.example.appmusic.Service;

import com.example.appmusic.Model.Advertisement;
import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.Category;
import com.example.appmusic.Model.Category_Theme;
import com.example.appmusic.Model.Comment;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.Model.Song;
import com.example.appmusic.Model.Theme;
import com.example.appmusic.Model.User;
//import com.example.appmusic.Model.PlayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//phuong thuc tuong tac voi server, server tra ve
public interface DataService {
    @GET("Server/songBanner.php")
        Call<List<Advertisement>> getDataBanner();

    @GET("Server/playlistforcurrentday.php")
    Call<List<PlayList>> getPlayListCurrentDay();

    // lấy dữ liêu chủ đề và thể loại trên CSDL
    @GET("Server/themeandcategoryinday.php")
    Call<Category_Theme> getCategoryMusic();

    // lay du lieu album hot
    @GET("Server/albumhot.php")
    Call<List<Album>> getAlbumHot();

    //lay du lieu cua bai hat duoc yeu thich
    @GET("Server/songlove.php")
    Call<List<Song>> getSongHot();

    //lay du lieu quang cao
    @FormUrlEncoded
    @POST("Server/listSong.php")
    Call<List<Song>> getDataSongAdver(@Field("IDAdver") String idAdver);

    // lay du lieu playlist
    @FormUrlEncoded
    @POST("Server/listSong.php")
    Call<List<Song>> getDataPlaylist(@Field("IDPlaylist") String idPlaylist);

    @GET("Server/listPlaylistssss.php")
    Call<List<PlayList>> getListPlayLists () ;

    @FormUrlEncoded
    @POST("updatelikes.php")
    Call<String> UpdateLikes(@Field("likes") String likes,@Field("IDSong") String IDSong);
//    $_POST['$IDSong']

    //sự kiện thể loại
    @FormUrlEncoded
    @POST("Server/listSong.php")
    Call<List<Song>> getListCategoryByTheme(@Field("idCategory") String idCategory);

    @GET("Server/AllTheme.php")
    Call<List<Theme>> getAllThemes();

    @FormUrlEncoded
    @POST("Server/CategoryByTheme.php")
    Call<List<Category>> getCactegoryByTheme(@Field("idTheme") String idTheme);

    // login, trả về mảng dữ liệu của User
    // tất cả những biến đưa vào để gửi lên Server phải trùng nhau
    @FormUrlEncoded
    @POST("Server/login.php")
    Call<List<User>> getDataUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("Server/listSong.php")
    Call<List<Song>> GetListSongByAlbum(@Field("idalbum") String idalbum);

    @GET("Server/listAlbums.php")
    Call<List<Album>> GetAllAlbum();

    @GET("Server/comment.php")
//    Call<List<Comment>> getDataComment(@Field("IDSong") String IDSong);
    Call<List<Comment>> getDataComment();
}
