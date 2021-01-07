package com.example.appmusic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.appmusic.Adapter.AdminAdapter;
import com.example.appmusic.Model.Admin;
import com.example.appmusic.Model.User;
import com.example.appmusic.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvFunctionAdmin;
    ArrayList<Admin> listFunctionAdmin;
    AdminAdapter adminAdapter;
    ArrayList<User> listUser;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
// lấy dữ liệu từ màn hình login qua (sửa user thành admin)
        Intent intent = getIntent();
        listUser = (ArrayList<User>) intent.getSerializableExtra("user");
//        addControls();
        addEvents();
    }

    private void addEvents() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("Hi admin: " + listUser.get(0).getUserName());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void addControls() {
        toolbar = (Toolbar) findViewById(R.id.toolBarFuctionAdmin);
        listFunctionAdmin = new ArrayList<>();
        lvFunctionAdmin = (ListView) findViewById(R.id.lvFunctionAdmin);
        registerForContextMenu(lvFunctionAdmin);
        listFunctionAdmin.add(new Admin("QUẢN LÝ NGƯỜI DÙNG", R.drawable.user_admin));
        listFunctionAdmin.add(new Admin("QUẢN LÝ NHẠC", R.drawable.music_admin));
        listFunctionAdmin.add(new Admin("THỐNG KÊ LƯỢT THÍCH", R.drawable.statistics_admin));
        listFunctionAdmin.add(new Admin("CHIẾN DỊCH MARKETING", R.drawable.marketing_admin));
        listFunctionAdmin.add(new Admin("THỐNG KÊ LƯỢT TẢI", R.drawable.download_admin));
        adminAdapter = new AdminAdapter(this, R.layout.line_admin, listFunctionAdmin);
        lvFunctionAdmin.setAdapter(adminAdapter);


    }
}