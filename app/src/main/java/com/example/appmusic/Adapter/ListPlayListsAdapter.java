package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListPlayListsActivity;
import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlayListsAdapter extends RecyclerView.Adapter<ListPlayListsAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> playLists;

    public ListPlayListsAdapter(Context context, ArrayList<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_list_playlists, parent, false);
        return new ListPlayListsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playLists.get(position);
        Picasso.with(context).load(playList.getImageIcon()).into(holder.imgListPlayList);
        holder.txtNameListPlayList.setText(playList.getNamePlayList());

    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    // View Holder để tăng tốc việc ánh xạ lại cho View
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameListPlayList;
        ImageView imgListPlayList;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNameListPlayList = itemView.findViewById(R.id.txtNameListPlayLists);
            imgListPlayList = itemView.findViewById(R.id.imgListPlayLists);

            // sự kiện khi nhấn vào hình từng ietm trong PlayList
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // gửi data và chuyển qua màn hình danh sách các bài hát
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemPlaylist", playLists.get(getPosition()));// key put vào trùng với key playlist
                    context.startActivity(intent);
                }
            });
        }
    }
}
