package com.example.appmusic.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adapter.ListSongAdapter;
import com.example.appmusic.Model.Advertisement;
import com.example.appmusic.Model.Category;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    Advertisement advertisement;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSong;
    FloatingActionButton floatingActionButton;
    ImageView imgList;
    AppBarLayout appBarLayout;
    ArrayList<Song> arrSong;
    View viewBackGround;
    NestedScrollView nestedScrollView;
    ListSongAdapter listSongAdapter;
    PlayList playList;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        getDataIntent();
        getData();
        addControls();
        init();
        // nếu quảng cáo tồn tại và tên bài hát 0 bằng rỗng
        if (advertisement != null && !advertisement.getNameSong().equals("")) {
            setValueInView(advertisement.getNameSong(), advertisement.getImageAdver());
            getDataAdver(advertisement.getIDAdver());
        }

        if (playList != null && !playList.getNamePlayList().equals("")) {
            setValueInView(playList.getNamePlayList(), playList.getImageIcon());
            getDataPlaylist(playList.getIDPlayList());
        }
        // nếu TheLoai tồn tại và tên the loai ko bằng rỗng
        if (category != null && !category.getNameCategory().equals("")) {
            setValueInView(category.getNameCategory(), category.getImageCategory());
            getDataCategory(category.getIDCategory());
        }
    }

    private void getDataCategory(String idCategory) {
        DataService dataService = APIServer.getService(); // khởi tạo  DataService, lấy đường dẫn
        Call<List<Song>> call=dataService.getListCategoryByTheme(idCategory);
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arrSong = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, arrSong);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this, RecyclerView.VERTICAL, false));
                recyclerViewListSong.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(final String idPlayList) {
        DataService dataService = APIServer.getService();
        Call<List<Song>> callback = dataService.getDataPlaylist(idPlayList);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arrSong = (ArrayList<Song>) response.body();
//                Log.d("BBBBBBBBBBB", arrSong.size()+"");
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, arrSong);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this, RecyclerView.VERTICAL, false));
                recyclerViewListSong.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("BBBBBBBBBBB", idPlayList.toString() + "fail");
            }
        });
    }

    private void getDataAdver(final String idAdver) {
        DataService dataService = APIServer.getService(); // khởi tạo  DataService, lấy đường dẫn
        Call<List<Song>> callBack = dataService.getDataSongAdver(idAdver);// gọi pthuc trả về mảng các Album
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                // sự kiện lăng nghe thành công
                arrSong = (ArrayList<Song>) response.body(); // trả về mảng dữ liệu
                // in ra xem kết quả
                Log.d("BBBBBBBBBBB", arrSong.get(0).getNameSong());
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, arrSong);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this, RecyclerView.VERTICAL, false));
                recyclerViewListSong.setAdapter(listSongAdapter);
                eventClick();
            }

            // sự kiện thất bại
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("BBBBBBBBBBB", arrSong.size() + "");
            }
        });
    }

    private void addControls() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
        viewBackGround = findViewById(R.id.viewBackGround);
        toolbar = findViewById(R.id.toolBarList);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerViewListSong = findViewById(R.id.rvListSong);
        imgList = findViewById(R.id.imgList);
        nestedScrollView = findViewById(R.id.nestScrollView);
        //txtTotalSong = findViewById(R.id.txtTotalSong);
    }

    private void setValueInView(String name, String image) {
        // đầu  tiên lấy dữ liệu bài hát set lại tên cho thanh toolbar
        collapsingToolbarLayout.setTitle(name);

        try {
            URL url = new URL(image);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            // convert về dạng Bitmap
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            //Toast.makeText(this, bitmapDrawable.toString(), Toast.LENGTH_LONG).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(image).into(imgList);
    }

    private void init() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void getData() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
//        appBarLayout = findViewById(R.id.appBarLayout);
//        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
//        viewBackGround = findViewById(R.id.viewBackGround);
        toolbar = findViewById(R.id.toolBarList);
        floatingActionButton = findViewById(R.id.floatingActionButton);
//        rvDanhSachBaiHat = findViewById(R.id.rvDanhSachBaiHat);
        imgList = findViewById(R.id.imgList);
//        nestedScrollView = findViewById(R.id.nestScrollView);


//        coordinatorLayout = findViewById(R.id.coordinatorLayout);
//        toolbar = findViewById(R.id.toolBarList);
        recyclerViewListSong = findViewById(R.id.rvListSong);
//        floatingActionButton = findViewById(R.id.floatingActionButton);

    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                advertisement = (Advertisement) intent.getSerializableExtra("banner");
//                Toast.makeText(this,advertisement.getNameSong(),Toast.LENGTH_LONG).show();
            }
            if (intent.hasExtra("itemPlaylist")) {
                playList = (PlayList) intent.getSerializableExtra("itemPlaylist");

            }
            if (intent.hasExtra("idCategory")) {
                category = (Category) intent.getSerializableExtra("idCategory");

            }

        }
    }

        // dua vao moi phuong thuc get du lieu
        private void eventClick(){
            floatingActionButton.setEnabled(true);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListSongActivity.this,PlayMusicActivity.class);
                    intent.putExtra("songs",arrSong);
                    startActivity(intent);
                }
            });
        }
}