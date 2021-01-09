package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Activity.ListPlayListsActivity;
import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Activity.ListThemeActivity;
import com.example.appmusic.Adapter.PlayListAdapter;
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
    TextView txttitleplaylist, txtviewxemthemplaylist;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> arrPlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        // tuong tac voi layout cua frament
        lvplaylist = view.findViewById(R.id.listviewplaylist);
        txttitleplaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtviewxemthemplaylist = view.findViewById(R.id.textviewviewmoreplaylist);

        //chuyển màn hình khi bấm xem thêm
        txtviewxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ListPlayListsActivity.class);
//                Log.d("hhhhh","intent");
                startActivity(intent);
            }
        });

        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIServer.getService();
        Call<List<PlayList>> callback = dataService.getPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                arrPlaylist = (ArrayList<PlayList>) response.body();
//                Log.d("BBB",arrPlaylist.get(0).getBackground()); ra
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1, arrPlaylist);
                lvplaylist.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(lvplaylist);
                lvplaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), ListSongActivity.class);
                        intent.putExtra("itemPlaylist",arrPlaylist.get(position));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
