package com.example.appmusic.Fragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.appmusic.Adapter.SearchSongAdapter;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIServer;
import com.example.appmusic.Service.DataService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Find extends Fragment {
    View view;
//    Toolbar toolbar;
    RecyclerView recyclerViewseachsong;
    TextView txtnotdata;
    SearchSongAdapter searchSongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
//       Ánh xạ
//        toolbar = view.findViewById(R.id.toolbarsearchsong);
        recyclerViewseachsong = view.findViewById(R.id.recycleviewsearchsong);
        txtnotdata = view.findViewById(R.id.textviewnotdata);
        //set thuộc tính thông qua activity
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setTitle("");
        //bật chế độ toolbar và menu
        setHasOptionsMenu(true);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
              @Override
       //Lắng nghe sự kiện
            public boolean onQueryTextSubmit(String query) {
               //Khi nhấn enter mới xử lí sự kiện
                SearchKeySong(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, menuInflater);

    }
private void SearchKeySong(String query){
    DataService dataService = APIServer.getService();
    // chỉ update 1 lần 1 lượt thích, update theo id bài hát
    Call<List<Song>> callback=dataService.getSearchSong(query);
    // gửi lên Server
    callback.enqueue(new Callback<List<Song>>() {
        @Override
        public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
            // chỉ update 1 lần 1 lượt thích, update theo id bài hát
            ArrayList<Song> arrListSong = (ArrayList<Song>) response.body();
            if(arrListSong.size() > 0){
                searchSongAdapter =new SearchSongAdapter(getActivity(),arrListSong);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
                recyclerViewseachsong.setLayoutManager(linearLayoutManager);
                recyclerViewseachsong.setAdapter(searchSongAdapter);

                txtnotdata.setVisibility(View.GONE);
                recyclerViewseachsong.setVisibility(View.VISIBLE);

            }else{
                recyclerViewseachsong.setVisibility(View.GONE);
                txtnotdata.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Call<List<Song>> call, Throwable t) {

        }
    });
}
}

