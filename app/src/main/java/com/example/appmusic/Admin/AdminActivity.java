package com.example.appmusic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
// lấy dữ liệu từ màn hình login qua ( admin)
        Intent intent = getIntent();
        listUser = (ArrayList<User>) intent.getSerializableExtra("admin");
        addControls();
        addEvents();
    }

    private void addEvents() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("Hello: " + listUser.get(0).getUserName());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //sự kiện khi nhấn vào từng item
        lvFunctionAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdminActivity.this, "" + listFunctionAdmin.get(position).getTitle(), Toast.LENGTH_LONG).show();
                if(position==0){
                    Intent intent = new Intent(AdminActivity.this, Manager_User_Activity.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(AdminActivity.this, Manager_Songs_Activity.class);
                    startActivity(intent);

                }
            }
        });
    }
    private void addControls() {
        toolbar = (Toolbar) findViewById(R.id.toolBarFuctionAdmin);
        listFunctionAdmin = new ArrayList<>();
        lvFunctionAdmin = (ListView) findViewById(R.id.lvFunctionAdmin);
        registerForContextMenu(lvFunctionAdmin);
        //thiết kế giao diện với hình ảnh
        listFunctionAdmin.add(new Admin("QUẢN LÝ NGƯỜI DÙNG", R.drawable.user_admin));
        listFunctionAdmin.add(new Admin("QUẢN LÝ NHẠC", R.drawable.music_admin));
        listFunctionAdmin.add(new Admin("THỐNG KÊ LƯỢT THÍCH", R.drawable.statistics_admin));
        listFunctionAdmin.add(new Admin("CHIẾN DỊCH MARKETING", R.drawable.marketing_admin));
        listFunctionAdmin.add(new Admin("THỐNG KÊ LƯỢT TẢI", R.drawable.download_admin));
        adminAdapter = new AdminAdapter(this, R.layout.line_admin, listFunctionAdmin);
        lvFunctionAdmin.setAdapter(adminAdapter);


    }
}