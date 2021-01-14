package com.example.appmusic.Admin;

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
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager_All_Songs_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvManagerSong;
    String urlData="https://oanhnguyen1999.000webhostapp.com/Server/managerSong.php";
    String urlDelete="https://oanhnguyen1999.000webhostapp.com/Server/deleteSong.php";
    ArrayList<Song> listSong;
//    ArrayList<Song> detailSong;
    Manager_All_Songs_Adapter managerSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_all_songs);

        addControls();
        getData(urlData);
        addEvents();
    }

    private void getData(String urlData) {
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
                        listSong.clear();
//                        detailSong.clear();

                        // đọc data JSON
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // JSONObject là từng đối tượng JSON (nằm trong {} )
                                JSONObject jsonObject = response.getJSONObject(i);
                                //Toast.makeText(MainActivity.this, "Xin chào" + jsonObject.getInt("IdBaiHat"), Toast.LENGTH_LONG).show();
//                                String name = jsonObject.getString("TenBaiHat");
//                                String singer = jsonObject.getString("CaSi");
//                                int image = jsonObject.getInt("HinhCaSi");

                                //Toast.makeText(MainActivity.this, name + "\t" + singer + "\t" + image, Toast.LENGTH_LONG).show();
                                // thêm vào ArrayList
//                                listSong.add(new Song(jsonObject.getString("IDSong"), jsonObject.getString("NameSong"),
//                                        jsonObject.getString("Singer"), jsonObject.getString("ImageSong")));
                                listSong.add(new Song(jsonObject.getString("IDSong"), jsonObject.getString("NameSong"),
                                        jsonObject.getString("Singer"), jsonObject.getString("ImageSong"),
                                        jsonObject.getString("IDAlbum"), jsonObject.getString("IDCategory"),
                                        jsonObject.getString("IDPlayList"),jsonObject.getString("LinkSong")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // sau khi chạy xong vòng for để có được dữ liệu thì mới gán Adapter
                        managerSongAdapter = new Manager_All_Songs_Adapter(Manager_All_Songs_Activity.this, R.layout.line_manager_all_songs, listSong);
                        lvManagerSong.setAdapter(managerSongAdapter);
                        // cập nhật lại giao diện cho ListView
                        managerSongAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Manager_All_Songs_Activity.this, "Lỗi: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    // Hàm xoá bài hát bên phần Quản Lý BaiHat
    public void deleteSong(final int id) {
        // Client gửi request lên Server
        // Volley là thư viện để truyền data qua mạng
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // phần nhận kết quả
                //test xoá id 48 & 56
                if (response.trim().equalsIgnoreCase("Success")) {
                    Toast.makeText(Manager_All_Songs_Activity.this, "Xoá thành công!", Toast.LENGTH_LONG).show();
                    // sau khi xoá thành công thì phải load lại data lên
                    getData(urlData);
                } else {

                    Toast.makeText(Manager_All_Songs_Activity.this, "Xoá thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // phần lỗi
                Toast.makeText(Manager_All_Songs_Activity.this, "Lỗi " + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("Lỗi Xoá bài hát", error.toString());
            }
        }) {
            @Override
            // khi request lên server có đính kèm thêm data
            // phía server sẽ lấy dữ liệu dựa vào key-value và thực hiện sau đó trả về Client
            protected Map<String, String> getParams() throws AuthFailureError {
                // khởi tạo Map để đẩy dữ liệu vào
                Map<String, String> map = new HashMap<>();
                map.put("IDSong", String.valueOf(id)); // key khi đẩy data lên phải trùng với key khi viết mã PHP để lấy
                return map;
            }
        };
        // thêm các request vào hàng đợi yêu cầu và chờ thực hiện
        requestQueue.add(stringRequest);
    }
    // tạo option menu (thanh MenuBar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_music, menu);
        return true;
    }
    // hàm xử lý khi chọn menu item nào
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // nếu chon menu add user thì chuyển qua AddSongActivity
        if (item.getItemId() == R.id.mnuAddSong) {
            Intent intent = new Intent(Manager_All_Songs_Activity.this, AddSongActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        setSupportActionBar(toolbar); // thay thế tool bar vì đã bỏ action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nút mũi tên quay lại
        getSupportActionBar().setTitle("Manager Songs");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbar = (Toolbar)findViewById(R.id.toolBarManagerSong);
        lvManagerSong = (ListView) findViewById(R.id.lvManagerSong);
        listSong = new ArrayList<>();

    }
}