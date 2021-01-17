package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Activity.LoginActivity;
import com.example.appmusic.Model.User;
import com.example.appmusic.R;

import java.util.ArrayList;

public class Fragment_nav_header extends Fragment {
    View view;
    //khai báo nav_header
    TextView NavigationUsername, NavigationAdmin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nav_header,container,false);
//ten user
        NavigationUsername = view.findViewById(R.id.txtUsername);

        NavigationUsername.setText(LoginActivity.nameUser);
        //admin
//        NavigationAdmin = view.findViewById(R.id.NavigationAdmin);
//        setData();
        return view;
    }

    private void setData() {
        NavigationAdmin.setText("Han");
    }


}
