package com.example.appmusic.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusic.Adapter.ListAlbumAdapter;
import com.example.appmusic.Model.Album;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlbumActivity extends AppCompatActivity {

    RecyclerView recyclerViewAlbum;
    Toolbar toolbarAlbum;
    ListAlbumAdapter listAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataservice = APIServer.getService();
        Call<List<Album>> callback = dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrAlbum = (ArrayList<Album>) response.body();
                listAlbumAdapter = new ListAlbumAdapter(ListAlbumActivity.this, arrAlbum);
                recyclerViewAlbum.setLayoutManager(new GridLayoutManager(ListAlbumActivity.this,2));
                recyclerViewAlbum.setAdapter(listAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAlbum = findViewById(R.id.recyclerviewAlbum);
        toolbarAlbum = findViewById(R.id.toolBarAlbum);
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Album");
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}