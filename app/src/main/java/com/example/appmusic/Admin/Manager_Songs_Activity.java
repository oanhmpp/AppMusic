package com.example.appmusic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appmusic.R;

public class Manager_Songs_Activity extends AppCompatActivity {
    Toolbar toolbar;
    CardView cardViewSong, cardViewAlbum, cardViewPlaylist, cardViewTheme, cardViewCategory;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_songs);
        addControls();
        addEvents();
    }
    private void addEvents() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("Manager Song");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbar = (Toolbar) findViewById(R.id.toolBarmanagerSong);
        linearLayout = (LinearLayout) findViewById(R.id.ll);
        cardViewSong = (CardView) findViewById(R.id.cardViewSong);
        cardViewAlbum = (CardView) findViewById(R.id.cardViewAlbum);
        cardViewPlaylist = (CardView) findViewById(R.id.cardViewPlaylist);
        cardViewTheme = (CardView) findViewById(R.id.cardViewTheme);
        cardViewCategory = (CardView) findViewById(R.id.cardViewCategory);
        //Intent intent = new Intent(this,ae.class);
        cardViewSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Manager_Songs_Activity.this, "Bài hát", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Manager_Songs_Activity.this, Manager_All_Songs_Activity.class);
                startActivity(intent);
            }
        });
        cardViewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(i);
                Toast.makeText(Manager_Songs_Activity.this, "Album", Toast.LENGTH_LONG).show();
            }
        });
        cardViewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(i);
                Toast.makeText(Manager_Songs_Activity.this, "Playlist", Toast.LENGTH_LONG).show();
            }
        });
        cardViewTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(i);
                Toast.makeText(Manager_Songs_Activity.this, "Chủ đề", Toast.LENGTH_LONG).show();
            }
        });
        cardViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(i);
                Toast.makeText(Manager_Songs_Activity.this, "Thể loại", Toast.LENGTH_LONG).show();
            }
        });

    }
}