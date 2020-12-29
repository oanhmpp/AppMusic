package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.appmusic.Model.Advertisement;
import com.example.appmusic.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListSongActivity extends AppCompatActivity {
    Advertisement advertisement ;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSong;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        getDataIntent();
        getData();
    }

    private void getData() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
//        appBarLayout = findViewById(R.id.appBarLayout);
//        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
//        viewBackGround = findViewById(R.id.viewBackGround);
        toolbar = findViewById(R.id.toolBarList);
        floatingActionButton = findViewById(R.id.floatingActionButton);
//        rvDanhSachBaiHat = findViewById(R.id.rvDanhSachBaiHat);
//        imgDanhSach = findViewById(R.id.imgDanhSach);
//        nestedScrollView = findViewById(R.id.nestScrollView);


//        coordinatorLayout = findViewById(R.id.coordinatorLayout);
//        toolbar = findViewById(R.id.toolBarList);
        recyclerViewListSong = findViewById(R.id.rvListSong);
//        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                advertisement = (Advertisement) intent.getSerializableExtra("banner");
//                Toast.makeText(this,advertisement.getNameSong(),Toast.LENGTH_LONG).show();
            }
        }
    }
}