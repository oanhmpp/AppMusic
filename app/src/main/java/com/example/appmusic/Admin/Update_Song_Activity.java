package com.example.appmusic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;

import java.util.HashMap;
import java.util.Map;

public class Update_Song_Activity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtChangeName, edtChangeAuthor, edtChangeImage;
//    EditText   edtChangeIDAlbum,edtChangeIDCategory,edtChangeIDPlayList,edtChangeLinkSong;
    Button btnEdit, btnCancel;
    String urlUpdate="https://oanhnguyen1999.000webhostapp.com/Server/updatesong.php";

    String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__update__song);

        // lấy dữ liệu từ MainActivity qua
        Intent intent = getIntent();
        Song baiHat = (Song) intent.getParcelableExtra("data");
        addcontrols();

        id = baiHat.getIDSong();
        Toast.makeText(Update_Song_Activity.this, id, Toast.LENGTH_LONG).show();
        edtChangeName.setText(baiHat.getNameSong().toString().trim());
        edtChangeAuthor.setText(baiHat.getSinger().toString().trim());
        edtChangeImage.setText(baiHat.getImageSong().toString().trim());
//        edtChangeIDAlbum.setText(baiHat.getImageSong().toString().trim());
//        edtChangeIDCategory.setText(baiHat.getImageSong().toString().trim());
//        edtChangeIDPlayList.setText(baiHat.getImageSong().toString().trim());
//        edtChangeLinkSong.setText(baiHat.getImageSong().toString().trim());


        addEvents();

    }
    private void addEvents() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy giá trị người dùng nhập
                String name = edtChangeName.getText().toString();
                String author = edtChangeAuthor.getText().toString();
                String image = edtChangeImage.getText().toString();
//                String idalbum = edtChangeIDAlbum.getText().toString();
//                String idcategory = edtChangeIDCategory.getText().toString();
//                String idplaylist = edtChangeIDPlayList.getText().toString();
//                String linksong = edtChangeLinkSong.getText().toString();

                if (name.isEmpty() || author.isEmpty() || image.isEmpty()) {
                    Toast.makeText(Update_Song_Activity.this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_LONG).show();
                } else {
                    updateSong(urlUpdate);
                }

            }
        });
    }
    // hàm cập nhật bài hát
    private void updateSong(String urlUpdate) {
        // Volley để quản lí các Request Network
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // phần nhận kết quả
                //Thanh cong nhung chua cap nhat
                if (response.trim().equalsIgnoreCase("Success")) {
                    Toast.makeText(Update_Song_Activity.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                    // sau đó chuyển màn hình về MainActivity
                    Intent intent = new Intent(Update_Song_Activity.this, Manager_All_Songs_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Update_Song_Activity.this, "Cập nhật thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_Song_Activity.this, "Lỗi " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // lấy giá trị người dùng nhập
                String name = edtChangeName.getText().toString().trim();
                String author = edtChangeAuthor.getText().toString().trim();
                String image = edtChangeImage.getText().toString().trim();
//                String idalbum = edtChangeIDAlbum.getText().toString().trim();
//                String idcategory = edtChangeIDCategory.getText().toString().trim();
//                String idplaylist = edtChangeIDPlayList.getText().toString().trim();
//                String linksong = edtChangeLinkSong.getText().toString().trim();



                // khởi tạo Map để đẩy dữ liệu vào
                Map<String, String> map = new HashMap<>();
                map.put("IDSong", id); // key khi đẩy data lên phải trùng với key khi viết mã PHP để lấy
                map.put("NameSong", name);
                map.put("Singer", author);
                map.put("ImageSong", image);
//                map.put("IDAlbum",idalbum);
//                map.put("IDCategory",idcategory);
//                map.put("IDPlayList",idplaylist);
//                map.put("LinkSong",linksong);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    // ánh xạ các component
    private void addcontrols() {
        toolbar = (Toolbar) findViewById(R.id.toolBarUpdateBaiHat);
        edtChangeName = (EditText) findViewById(R.id.edtChangeName);
        edtChangeAuthor = (EditText) findViewById(R.id.edtChangeAuthor);
        edtChangeImage = (EditText) findViewById(R.id.edtChangeImage);
//        edtChangeIDAlbum=findViewById(R.id.edtChangeIDAlbum);
//        edtChangeIDCategory=findViewById(R.id.edtChangeIDCategory);
//        edtChangeIDPlayList=findViewById(R.id.edtChangeIDPlayList);
//        edtChangeLinkSong=findViewById(R.id.edtChangeLinkSong);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnCancel = (Button) findViewById(R.id.btnUpdateCancel);

    }
}