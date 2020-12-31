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

import com.example.appmusic.Activity.PlayMusicActivity;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arrSong;

    public ListSongAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.line_list_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arrSong.get(position);
        holder.tvSinger.setText(song.getSinger());
        holder.tvNameSong.setText(song.getNameSong());
        holder.tvListIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvListIndex, tvNameSong, tvSinger;
        ImageView imageViewLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListIndex = itemView.findViewById(R.id.tvListIndex);
            tvNameSong = itemView.findViewById(R.id.tvNameSong);
            tvSinger = itemView.findViewById(R.id.tvSinger);
            imageViewLikes = itemView.findViewById(R.id.imageViewLikes);

       // xử lí khi nhấn vào từng item bài hát
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // chuyển dữ liệu qua màn hình play nhạc
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic", arrSong.get(getPosition())); // truyền nguyên đối tượng bài hát qua màn hình Play nhac
                    context.startActivity(intent);
                }
            });
        }
    }
}
