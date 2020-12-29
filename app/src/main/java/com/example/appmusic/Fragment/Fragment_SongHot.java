package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adapter.SongHotAdapter;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_SongHot extends Fragment {

    View view;
    RecyclerView recyclerViewSongHot;
    SongHotAdapter songHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_hot,container,false);
        recyclerViewSongHot = view.findViewById(R.id.recyclerviewSong);
        getData();
        return view;
    }
// khoi tao class cua data service
    private void getData() {
        DataService dataService = APIServer.getService();
        Call<List<Song>> callback = dataService.getSongHot();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> arrSong =  (ArrayList<Song>) response.body();
//                Log.d("BBB", arrSong.get(0).getNameSong());
                songHotAdapter = new SongHotAdapter(getActivity(),arrSong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewSongHot.setLayoutManager(linearLayoutManager);
                recyclerViewSongHot.setAdapter(songHotAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
