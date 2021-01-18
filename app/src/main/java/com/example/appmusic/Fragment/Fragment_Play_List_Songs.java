package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Activity.PlayMusicActivity;
import com.example.appmusic.Adapter.PlayMusicAdapter;
import com.example.appmusic.R;

public class Fragment_Play_List_Songs extends Fragment {
    View view;
    RecyclerView recyclerViewPlayMusic;
    PlayMusicAdapter playMusicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_play_lists,container,false);
        recyclerViewPlayMusic = view.findViewById(R.id.rvListPlayLists);
        if(PlayMusicActivity.arrSong.size()>0){
            playMusicAdapter  = new PlayMusicAdapter(getActivity(), PlayMusicActivity.arrSong);

            recyclerViewPlayMusic.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
//            new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
//            recyclerViewPlayMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayMusic.setAdapter(playMusicAdapter);
        }
        return view;
    }
}
