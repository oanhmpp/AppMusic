package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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


public class Fragment_Playlist extends Fragment {
    //ánh xạ cho playlist
    View view;
    ListView lvplaylist;
    TextView txttitleplaylist,txtviewxemthemplaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_playlist,container,false);
       lvplaylist = view.findViewById(R.id.listviewplaylist);
       txttitleplaylist=view.findViewById(R.id.textviewtitleplaylist);
       txtviewxemthemplaylist = view.findViewById(R.id.textviewviewmoreplaylist);
       
         getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIServer.getService();
        Call<List<PlayList>> callback =dataService.getPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> arrPlaylist= (ArrayList<PlayList>) response.body();
//                Log.d("BBB",arrPlaylist.get(0).getNamePlayList());
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
