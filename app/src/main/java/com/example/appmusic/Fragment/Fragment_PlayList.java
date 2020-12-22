package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_playlist,container,false);
        getData();
        return view;
    }

    private void getData() {
        //cấu hình lại
//        su dung
        DataService dataService= APIServer.getService();
        Call<List<PlayList>> callback=dataService.getPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
//            sự kiện lắng nghe
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> arrayPlaylist= (ArrayList<PlayList>) response.body();
                Log.d("Playlist",arrayPlaylist.get(0).getIDPlayList());
            }

            @Override
//            sự kiện thất bại
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });

    }
}
