package com.example.appmusic.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusic.Adapter.ListPlayListsAdapter;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPlayListsActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rvListPlayLists;
    ArrayList<PlayList> arrPlayLists;
    ListPlayListsAdapter listPlayListsAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_play_lists);
        getMapping();
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIServer.getService(); // khởi tạo  DataService, lấy đường dẫn
        Call<List<PlayList>> callBack = dataService.getListPlayLists();// gọi pthuc trả về mảng các Album
        callBack.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                // sự kiện lăng nghe thành công
                arrPlayLists = (ArrayList<PlayList>) response.body(); // trả về mảng dữ liệu
                // in ra xem kết quả
//                Log.d("BBBBBBBBBBB", arrPlayLists.get(0).getNamePlayList());
//                Log.d("BBBBBBBBBBB", playLists.get(0).getIcon());
//                Log.d("BBBBBBBBBBB", playLists.get(0).getHinhPlayList());
//                 gắn phần apdater lên
                listPlayListsAdapter = new ListPlayListsAdapter(ListPlayListsActivity.this, arrPlayLists);
//                 hiển thị lên RecycleView ở đây, cho layout nào thì nó sẽ hiện thị dạng đó, Grid thì phải thêm số cột nữa
//                 hiển thị dạng Grid với 2 cột
                rvListPlayLists.setLayoutManager(new GridLayoutManager(ListPlayListsActivity.this, 2));
                rvListPlayLists.setAdapter(listPlayListsAdapter);
            }

            // sự kiện thất bại
            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("PlayList");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getMapping() {
        toolbar = findViewById(R.id.toolBarPlayList);
        rvListPlayLists = findViewById(R.id.rvListPlayLists);
    }
}