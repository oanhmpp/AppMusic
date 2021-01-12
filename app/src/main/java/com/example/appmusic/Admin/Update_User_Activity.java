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
import com.example.appmusic.Model.User;
import com.example.appmusic.R;

import java.util.HashMap;
import java.util.Map;

public class Update_User_Activity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtUpdateUsername, edtUpdatePassword, edtUpdateAdmin;
    Button btnEdit, btnCancel;
    String urlUpdate="https://oanhnguyen1999.000webhostapp.com/Server/updateuser.php";
    String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        // lấy dữ liệu từ MainActivity qua
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("data");

        addcontrols();
        username = user.getUserName();

        edtUpdateUsername.setText(user.getUserName().toString().trim());
        edtUpdatePassword.setText(user.getPassword().toString().trim());
        edtUpdateAdmin.setText(user.getAdmin().toString().trim());
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
                String username = edtUpdateUsername.getText().toString();
                String password = edtUpdatePassword.getText().toString();
                String role = edtUpdateAdmin.getText().toString();
                if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                    Toast.makeText(Update_User_Activity.this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_LONG).show();
                } else {
                    updateUser(urlUpdate);
                }

            }
        });

    }
    // hàm cập nhật user
    private void updateUser(String urlUpdate) {
        // Volley để quản lí các Request Network
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // phần nhận kết quả
                if (response.trim().equalsIgnoreCase("Success")) {
                    Toast.makeText(Update_User_Activity.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                    // sau đó chuyển màn hình về MainActivity
                    Intent intent = new Intent(Update_User_Activity.this, Manager_User_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Update_User_Activity.this, "Cập nhật thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_User_Activity.this, "Lỗi " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // lấy giá trị người dùng nhập
                String UserName = edtUpdateUsername.getText().toString();
                String Password = edtUpdatePassword.getText().toString();
                String Admin = edtUpdateAdmin.getText().toString();

                // khởi tạo Map để đẩy dữ liệu vào
                Map<String, String> map = new HashMap<>();
                map.put("UserName", UserName);// key khi đẩy data lên phải trùng với key khi viết mã PHP để lấy
                map.put("Password", Password);
                map.put("Admin", Admin);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void addcontrols() {
        toolbar = (Toolbar) findViewById(R.id.toolBarUpdateUser);
        edtUpdateUsername = (EditText) findViewById(R.id.edtUpdateUsername);
        edtUpdatePassword = (EditText) findViewById(R.id.edtUpdatePassword);
        edtUpdateAdmin = (EditText) findViewById(R.id.edtUpdateAmin);
        btnEdit = (Button) findViewById(R.id.btnUpdateUser);
        btnCancel = (Button) findViewById(R.id.btnUpdateCancel);

    }
}