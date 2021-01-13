package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmusic.Admin.AdminActivity;
import com.example.appmusic.Model.User;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText txtUserName, txtPass;
    Button btnSign;
    String user_name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControl();
        addEvents();

    }

    private void addEvents() {
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = txtUserName.getText().toString();
                password = txtPass.getText().toString();

                if(user_name.length()>0 && password.length()>0){
                    DataService dataService = APIServer.getService();
                    Call<List<User>> callback = dataService.getDataUser(user_name,password);
                    callback.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            ArrayList<User> arrUser = (ArrayList<User>) response.body();
                            if(arrUser.size()>0){
                                Log.d("BBBBBBBBBB", arrUser.get(0).getUserName());
                                Log.d("fffff", arrUser.get(0).getPassword());
                                Log.d("hhhhh", arrUser.get(0).getAdmin());

                                // nếu là admin thì gửi qua trang cho admin
                                if (arrUser.get(0).getAdmin().equalsIgnoreCase("1")){
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công với tư cách là admin !", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                    intent.putExtra("admin", arrUser);
                                    startActivity(intent);
                                }
                                // nếu là user thì vào giao diện chính
                                else {
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công !", Toast.LENGTH_LONG).show();
                                    Intent getData = getIntent();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("user", arrUser);
                                    startActivity(intent);
                                }
                            }else{
                                Toast.makeText(LoginActivity.this,"Tài khoản không tồn tại",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Không tồn tại tài khoản!", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Các trường không được bỏ trống !!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addControl() {
        txtPass = (EditText) findViewById(R.id.txtPassword);
        txtUserName = (EditText) findViewById(R.id.txtUserName);

        btnSign = (Button) findViewById(R.id.btnSign);
    }

}