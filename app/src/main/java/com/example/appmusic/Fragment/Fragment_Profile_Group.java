package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.R;


public class Fragment_Profile_Group extends Fragment {
    View view;
    ImageView imgOanh, imgSon, imghang, imgThanh, imgMain;
    TextView txtOanh, txtSon, txtHang, txtThanh, txtPosition1, txtPosition2, txtPosition3, txtPosition4, txtMain;
    Button btnOanh, btnSon, btnHang, btnThanh;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_group, container, false);
        imgMain = view.findViewById(R.id.imgMain);
        imgOanh = view.findViewById(R.id.imgOanh);
        imgSon = view.findViewById(R.id.imgSon);
        imghang = view.findViewById(R.id.imgHang);
        imgThanh = view.findViewById(R.id.imgThanh);
        txtMain = view.findViewById(R.id.txtMain);
        txtOanh = view.findViewById(R.id.txtOanh);
        txtSon = view.findViewById(R.id.txtSon);
        txtHang = view.findViewById(R.id.txtHang);
        txtThanh = view.findViewById(R.id.txtThanh);
        txtPosition1 = view.findViewById(R.id.txtPosition1);
        txtPosition2 = view.findViewById(R.id.txtPosition2);
        txtPosition3 = view.findViewById(R.id.txtPosition3);
        txtPosition4 = view.findViewById(R.id.txtPosition4);

        btnOanh = view.findViewById(R.id.btnOanh);
        btnSon = view.findViewById(R.id.btnSon);
        btnHang = view.findViewById(R.id.btnHang);
        btnThanh = view.findViewById(R.id.btnThanh);

        btnOanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đang làm tiếp....", Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }
}
