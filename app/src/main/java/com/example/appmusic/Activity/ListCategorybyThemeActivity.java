package com.example.appmusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adapter.ListCategoryByThemeAdapter;
import com.example.appmusic.Model.Category;
import com.example.appmusic.Model.Theme;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.appmusic.Adapter.ListCategoryByThemeAdapter;
//import com.example.appmusic.Model.Category;
//import com.example.appmusic.Model.Theme;
//import com.example.appmusic.R;
//import com.example.appmusic.Service.APIServer;
//import com.example.appmusic.Service.DataService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
public class ListCategorybyThemeActivity extends AppCompatActivity {
    Theme theme;
    Toolbar toolBarCategoryByTheme;
    RecyclerView rvListCategoryByTheme;
    ListCategoryByThemeAdapter listCategoryByThemeAdapter;
//    ArrayList<Category> listCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_category);
        getDataIntent();
        init();
//        addControls();
        getDataAllAlbum();
//        addEvents();
    }

    private void init() {
        rvListCategoryByTheme = findViewById(R.id.rvListCategoryByTheme);
        toolBarCategoryByTheme = findViewById(R.id.toolBarCategoryByTheme);
        setSupportActionBar(toolBarCategoryByTheme);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(theme.getNameTheme());
        toolBarCategoryByTheme.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //
//    private void addEvents() {
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void addControls() {
//        toolbar = findViewById(R.id.toolBarTheLoaiTheoChuDe);
//        rvDanhSachTheLoaiTheoChuDe = findViewById(R.id.rvDanhSachTheLoaiTheoChuDe);
//        // tạo cái nút trên thanh toolbar để quay về
//        // vì đã xoá action bar đi rồi nên dùng getSupport
//        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
//        getSupportActionBar().setTitle(Theme.getNameTheme());
//
//    }
    // lấy data tất cả album
    private void getDataAllAlbum() {
        DataService dataService= APIServer.getService();// khởi tạo  DataService, lấy đường dẫn
        Call<List<Category>> callBack = dataService.getCactegoryByTheme(theme.getIDTheme());
        callBack.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                ArrayList<Category> listCategory= (ArrayList<Category>) response.body();
//                Log.d("HHHHHH",listCategory.get(0).getNameCategory());
                //custome lại view và đư dữ liệu vào
                listCategoryByThemeAdapter = new ListCategoryByThemeAdapter(ListCategorybyThemeActivity.this,listCategory);
                rvListCategoryByTheme.setLayoutManager(new GridLayoutManager(ListCategorybyThemeActivity.this,2));
                rvListCategoryByTheme.setAdapter(listCategoryByThemeAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
//            @Override
//            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
//                // sự kiện lăng nghe thành công
//                listTheLoai = (ArrayList<TheLoai>) response.body(); // trả về mảng dữ liệu
//                // in ra xem kết quả
////                Log.d("BBBBBBBBBBB", listAlbums.get(0).getTenAlbum());
////                Log.d("BBBBBBBBBBB", listAlbums.get(0).getHinhAnhAlbum());
////                Log.d("BBBBBBBBBBB", listAlbums.get(0).getTenCasiAlbum());
//                // gắn phần apdater lên
//                danhSachTheLoaiTheoChuDeAdapter = new ListCategoryByThemeAdapter(ListCategoryByThemeAdapter.this, listTheLoai);
//                // hiển thị lên RecycleView ở đây, cho layout nào thì nó sẽ hiện thị dạng đó, Grid thì phải thêm số cột nữa
//                // hiển thị dạng Grid với 2 cột
//                rvDanhSachTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this, 2));
////                rvDanhSachAlbum.addItemDecoration(new GridSpacingItemDecoration(2, dp));
//                rvDanhSachTheLoaiTheoChuDe.setItemAnimator(new DefaultItemAnimator());
//                rvDanhSachTheLoaiTheoChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
//
            }
//
//            // sự kiện thất bại
//            @Override
//            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
//
//            }
//        });
//    }
//
    // lấy nội dung từ màn hình DanhSachAllChuDe gửi qua
    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("Theme")) {
            theme = (Theme) intent.getSerializableExtra("Theme");
//            Toast.makeText(ListCategorybyThemeActivity.this,theme.getNameTheme(), Toast.LENGTH_LONG).show();
        }

    }
}
