package com.example.appmusic.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmusic.Model.User;
import com.example.appmusic.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager_User_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvManagerUser;
    FloatingActionButton fabAddUser;
    ArrayList<User> listUsers;
    Manager_User_Adapter managerUserAdapter;
    String urlData = "https://oanhnguyen1999.000webhostapp.com/Server/user.php";
    String urlDelete="https://oanhnguyen1999.000webhostapp.com/Server/deleteUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__user_);
        addControls();
        getData(urlData);
        addEvents();
    }

    public void getData(String urlData) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // lấy dữ liệu nên dùng GET
        // JSONArray là một mảng các đối tưởng JSON (dấu [])
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlData, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Toast.makeText(QuanLyUserActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        //Log.i("BBBBBBB", response.toString());
                        // trước khi load thì xoá hết cái cũ đi
                        listUsers.clear();
                        // đọc data JSON
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // JSONObject là từng đối tượng JSON (nằm trong {} )
                                JSONObject jsonObject = response.getJSONObject(i);
//                                Toast.makeText(Manager_User_Activity.this, "Xin chào" + jsonObject.getInt("UserName"), Toast.LENGTH_LONG).show();
//                                String name = jsonObject.getString("TenBaiHat");
//                                String singer = jsonObject.getString("CaSi");
//                                int image = jsonObject.getInt("HinhCaSi");

                                //Toast.makeText(MainActivity.this, name + "\t" + singer + "\t" + image, Toast.LENGTH_LONG).show();
                                // thêm vào ArrayList
                                listUsers.add(new User(jsonObject.getString("UserName"), jsonObject.getString("Password"), jsonObject.getString("Admin")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // sau khi chạy xong vòng for để có được dữ liệu thì mới gán Adapter
                        managerUserAdapter = new Manager_User_Adapter(Manager_User_Activity.this, R.layout.line_manager_user, listUsers);
                        lvManagerUser.setAdapter(managerUserAdapter);
                        // cập nhật lại giao diện cho ListView
                        managerUserAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Manager_User_Activity.this, "Lỗi: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    //taoj thanh menu bar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
        return true;
    }
    //Hàm xoá user
    public void deleteUser (final String  userName) {
        // Client gửi request lên Server
        // Volley là thư viện để truyền data qua mạng
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // phần nhận kết quả
                if (response.trim().equalsIgnoreCase("Success")) {
                    Toast.makeText(Manager_User_Activity.this, "Xoá thành công!", Toast.LENGTH_LONG).show();
                    // sau khi xoá thành công thì phải load lại data lên
                    getData(urlData);
                } else {
                    Toast.makeText(Manager_User_Activity.this, "Xoá thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // phần lỗi
                Toast.makeText(Manager_User_Activity.this, "Lỗi " + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("Lỗi Xoá User", error.toString());
            }
        }) {
            @Override
            // khi request lên server có đính kèm thêm data
            // phía server sẽ lấy dữ liệu dựa vào key-value và thực hiện sau đó trả về Client
            protected Map<String, String> getParams() throws AuthFailureError {
                // khởi tạo Map để đẩy dữ liệu vào
                Map<String, String> map = new HashMap<>();
                map.put("UserName", userName); // key khi đẩy data lên phải trùng với key khi viết mã PHP để lấy
                return map;
            }
        };
        // thêm các request vào hàng đợi yêu cầu và chờ thực hiện
        requestQueue.add(stringRequest);

    }


    // hàm xử lý khi chọn menu item nào
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // nếu chon menu add user thì chuyển qua AddUserActivity
        if (item.getItemId() == R.id.mnuAddUser) {
            Intent intent = new Intent(Manager_User_Activity.this, AddUserActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    // hàm kiểm tra cho Context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(Manager_User_Activity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }


    private void addEvents() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("Quản Lý User");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbar = (Toolbar) findViewById(R.id.toolBarManagerUser);
        lvManagerUser = (ListView) findViewById(R.id.lvManagerUser);
        listUsers = new ArrayList<>();
        fabAddUser = (FloatingActionButton) findViewById(R.id.fabAddUser);
    }
}